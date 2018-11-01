package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/12/23
 */
@Getter
public enum HouseType {

    HOUSE_TYPE_ALL("all",0), HOUSE_TYPE_SPECIFIC("specific",2);

    private String name;
    private int value;
    HouseType(String name,int value){
        this.name = name;
        this.value = value;
    }
}
