package com.url_shortener.Util;

public class DataSourceContext {

    private static final ThreadLocal<String> CONTEXT=new ThreadLocal<>();

    public static void setDataSource(String ds){
        CONTEXT.set(ds);
    }

    public static String getDataSource(){
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
