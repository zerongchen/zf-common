package com.aotain.common.config.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 系统配置信息字典表
 *
 * @author daiyh@aotain.com
 * @date 2018/02/06
 */
@Getter
@Setter
public class SystemConfigDict {

    private Integer configId;

    private String configKey;

    private String configValue;

    private String configDesc;

    private Integer inputType;

    private String inputItems;

    private Integer modelType;

    private String createOper;

    private String modifyOper;

    private Date createTime;

    private Date modifyTime;

}
