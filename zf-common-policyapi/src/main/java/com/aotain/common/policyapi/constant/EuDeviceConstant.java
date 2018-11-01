package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * EU状态相关常量类
 *
 * @author daiyh@aotain.com
 * @date 2017/11/30
 */
public class EuDeviceConstant {

    @Getter
    /**
     * EU状态上报类型
     */
    private enum EuDeviceType{
        STATIC("static",1),DYNAMIC("dynamic",2);
        private String name;
        private Integer value;
        EuDeviceType(String name,Integer value) {
            this.name = name;
            this.value = value;
        }
    }

    @Getter
    /**
     * EU状态上报频率
     */
    private enum EuUploadFreq{

    }

    @Getter
    public enum EuDeviceStatus{
        NORMAL("normal",0),FATAL("fatal",1);
        private String name;
        private Integer value;
        EuDeviceStatus(String name,Integer value) {
            this.name = name;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.out.println("===="+ EuDeviceType.DYNAMIC.getValue());
    }
}
