package com.aotain.common.utils.monitorstatistics;

import java.io.Serializable;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/12/14
 */
public class TypeConstant implements Serializable{

    /**
     * DB错误
     */
    public static final String EXCEPTION_TYPE_DB = "db";
    /**
     * redis错误
     */
    public static final String EXCEPTION_TYPE_REDIS = "redis";
    /**
     * kafka错误
     */
    public static final String EXCEPTION_TYPE_KAFKA = "kafka";
    /**
     * 应用异常
     */
    public static final String EXCEPTION_TYPE_APPLICATION = "application";
    /**
     * 其它错误
     */
    public static final String EXCEPTION_TYPE_OTHER = "other";
    /**
     * 线程错误
     */
    public static final String EXCEPTION_TYPE_THREAD = "thread";
    /**
     * hive查询出错
     */
    public static final String EXCEPTION_TYPE_HIVE = "hive";

}
