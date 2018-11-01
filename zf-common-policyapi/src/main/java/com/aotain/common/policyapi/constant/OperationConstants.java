package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * 数据库操作常量类
 *
 * @author daiyh@aotain.com
 * @date 2017/11/20
 */
@Getter
public class OperationConstants {

    public static final int OPERATION_SAVE = 1;

    public static final int OPERATION_UPDATE = 2;

    public static final int OPERATION_DELETE = 3;

    public static final int OPERATION_EXPORT = 7;

    /** 重发 */
    public static final int OPERATION_RESEND = 8;
    
    /** 下发 */
    public static final int OPERATION_SEND = 12;
    /** 设置   */
    public static final int OPERATION_SET = 13;
}
