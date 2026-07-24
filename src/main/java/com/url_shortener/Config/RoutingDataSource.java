package com.url_shortener.Config;

import com.url_shortener.Util.DataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey(){
        System.out.println("Current DB = "+ DataSourceContext.getDataSource());
        return DataSourceContext.getDataSource();
    }
}
