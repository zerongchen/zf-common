package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/11/28
 */
@Getter
public enum ConnectFlag {
    SUCCESS("ConnectFlag_SUCCESS",(short)0), ERROR("ConnectFlag_ERROR",(short)1);

    private String name;
    private Short value;
    ConnectFlag(String name,Short value){
        this.name = name;
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println("========"+ConnectFlag.SUCCESS.getValue());
    }
}
