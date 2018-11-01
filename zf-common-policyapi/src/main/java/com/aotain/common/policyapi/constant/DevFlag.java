package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/11/28
 */
@Getter
public enum DevFlag {
    DPI("DevFlag_DPI",0), IDC("DevFlag_IDC",1);

    private String name;
    private Integer value;
    DevFlag(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println("========"+DevFlag.IDC.getValue());
    }
}
