package com.aotain.common.config.pagehelper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/03/01
 */
@Getter
@Setter
public class TimeScope {
    private String startTime;
    private String endTime;

    /** 将前端传递的时间增加时分秒 */
    public void setStartTime(String startTime){
        if (StringUtils.isEmpty(startTime)){
            return;
        }
        this.startTime = startTime + " 00:00:00";
    }

    /** 将前端传递的时间增加时分秒 */
    public void setEndTime(String endTime){
        if (StringUtils.isEmpty(endTime)){
            return;
        }
        this.endTime = endTime + " 23:59:59";
    }
}
