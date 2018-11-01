package com.aotain.common.policyapi.constant;
import lombok.Getter;

/**
 * 
* @ClassName: EuDeviceConstant 
* @Description: 状态相关常量类(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午4:59:01 
*
 */
public class DpiDeviceConstant {

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

}
