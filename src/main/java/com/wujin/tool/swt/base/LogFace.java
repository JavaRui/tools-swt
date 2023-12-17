package com.wujin.tool.swt.base;

import org.slf4j.Logger;

public  class LogFace {

    public static Logger logger;

    public static  void setLogger(Logger logger){
        LogFace.logger = logger;
    }

    public static void log( Object log){
        if(logger != null){
            logger.info(log+"");
        }else{
            System.out.println(log);
        }
    }

    public static void log(Class<?> clsName, Object log){
        if(logger != null){
            logger.info(clsName.toString(),log);
        }else{
            System.out.println(clsName+"  "+log);
        }
    }


    public static void err( Object log){
        if(logger != null){
            logger.error(log+"");
        }else{
            System.err.println(log);
        }
    }

    public static void err(Class<?> clsName, Object log){
        if(logger != null){
            logger.error(clsName.toString(),log);
        }else{
            System.err.println(clsName+"  "+log);
        }
    }

}
