package com.aotain.common.policyapi.constant;

import lombok.Getter;

/**
 *
 * @ClassName: UserGroupAction
 * @Description: 用户组归属策略的操作类型(这里用一句话描述这个类的作用)
 * @author DongBoye
 * @date 2018年1月18日 下午4:45:13
 *
 */
@Getter
public enum UserGroupAction {
    ADD("添加",1),DELETE("删除",2);

    private String name;

    private Integer value;

    UserGroupAction(String name, Integer value){
        this.name = name;
        this.value = value;
    }
}
