package com.url_shortener.Config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
public class DataSourceConfig {

    //DB1
    @Value("${spring.datasource.url}")
    private String db0Url;
    @Value("${spring.datasource.username}")
    private String db0Username;
    @Value("${spring.datasource.password}")
    private String db0Password;
    @Value("${spring.datasource.driver-class-name}")
    private String db0Driver;

    //DB1
    @Value("${db1.url}")
    private String db1Url;
    @Value("${db1.username}")
    private String db1Username;
    @Value("${db1.password}")
    private String db1Password;
    @Value("${db1.driver-class-name}")
    private String db1Driver;

    //DB2
    @Value("${db2.url}")
    private String db2Url;
    @Value("${db2.username}")
    private String db2Username;
    @Value("${db2.password}")
    private String db2Password;
    @Value("${db2.driver-class-name}")
    private String db2Driver;

    //DB3
    @Value("${db3.url}")
    private String db3Url;
    @Value("${db3.username}")
    private String db3Username;
    @Value("${db3.password}")
    private String db3Password;
    @Value("${db3.driver-class-name}")
    private String db3Driver;

    private String dbDriver,dbUrl,dbUsername,dbPassword;



    @Bean
    public DataSource dataSource() {

        DataSource db0=createDb(0);
        DataSource db1=createDb(1);
        DataSource db2=createDb(2);
        DataSource db3=createDb(3);
        Map<Object, Object> targetDataSources = new HashMap<>();

        targetDataSources.put("DB1", db1);
        targetDataSources.put("DB2", db2);
        targetDataSources.put("DB3", db3);

        RoutingDataSource routing = new RoutingDataSource();
        routing.setTargetDataSources(targetDataSources);
        routing.setDefaultTargetDataSource(db0);

        return routing;
    }

    private DataSource createDb(int shard) {
        if(shard==0){
            dbDriver=db0Driver;
            dbUrl=db0Url;
            dbUsername=db0Username;
            dbPassword=db0Password;
        }
        else if(shard==1){
            dbDriver=db1Driver;
            dbUrl=db1Url;
            dbUsername=db1Username;
            dbPassword=db1Password;
        }
        else if(shard==2){
            dbDriver=db2Driver;
            dbUrl=db2Url;
            dbUsername=db2Username;
            dbPassword=db2Password;
        }
        else{
            dbDriver=db3Driver;
            dbUrl=db3Url;
            dbUsername=db3Username;
            dbPassword=db3Password;
        }
        System.out.println("Shard "+shard+" "+dbUrl);
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);
        return ds;
    }
}
