package com.aotain.common.utils.monitorstatistics;

/**
 * 异常公共基础类
 *
 * @author daiyh@aotain.com
 * @date 2017/12/14
 */
public class MyException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * @see ModuleConstant
     * 错误所属模块
     */
    private String module;

    /**
     *
     * 错误码
     */
    private int errorCode;

    /**
     * @see      TypeConstant
     * 错误类型   redis kafka application db other
     */
    private String type;

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String type, int errorCode,String module, String message) {
        super(message);
        this.type = type;
        this.module = module;
        this.errorCode = errorCode;
    }

    public MyException(String type, String module, String message) {
        super(message);
        this.type = type;
        this.module = module;
        this.errorCode = 0;
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String type, int errorCode,String module, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
        this.module = module;
        this.errorCode = errorCode;
    }

    public String getModule() {
        return module;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getType() {
        return type;
    }
}
