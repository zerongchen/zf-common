package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/11/28
 */
@Getter
public enum ProbeType {

    DPI("ProbeType_DPI",0), EU("ProbeType_EU",1);

    private String name;
    private Integer value;
    ProbeType(String name,Integer value){
        this.name = name;
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println("========"+ProbeType.EU.getValue());
    }
}
