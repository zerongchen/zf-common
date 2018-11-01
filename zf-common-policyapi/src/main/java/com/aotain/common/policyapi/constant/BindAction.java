package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 * 绑定操作枚举类
 *
 * @author daiyh@aotain.com
 * @date 2017/11/29
 */
@Getter
public enum BindAction {

    BIND("绑定",1),UNBIND("解绑",2);

    private String name;

    private Integer value;

    BindAction(String name,Integer value){
        this.name = name;
        this.value = value;
    }
}
