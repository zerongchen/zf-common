package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 应用用户统计策略实体类
 *
 * @author daiyh@aotain.com
 * @date 2018/03/05
 */
@Getter
@Setter
public class AppUserStrategy extends BaseVO implements Serializable{

    private static final long serialVersionUID = 1L;

    @JSONField(serialize = false)
    private String appTypeName;

    @JSONField(name="rStartTime")
    private Long countStartTime;

    @JSONField(name="rEndTime")
    private Long countEndTime;

    private Integer appType;

    private Integer appId;

    private Integer userType;

    private String appName;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private List<Long> userGroupId;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private List<String> userName;

    private String userNames;

    private String userGroupIds;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private Date createTime;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private Date modifyTime;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String createOper;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String modifyOper;

    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private Long rStartTime;

    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private Long rEndTime;

    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String appPolicy;

    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String bindPolicy;

    public void setCountStartTime(String countStartTime) throws ParseException {
        try{
            this.countStartTime = Long.valueOf(countStartTime);
        } catch (Exception e){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.countStartTime = simpleDateFormat.parse(countStartTime+" 00:00:00").getTime()/1000;
        }

    }

    public void setCountEndTime(String countEndTime) throws ParseException {
        try{
            this.countEndTime = Long.valueOf(countEndTime);
        } catch (Exception e){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.countEndTime = simpleDateFormat.parse(countEndTime+" 00:00:00").getTime()/1000;
        }

    }

    public void setUserName() {
        List<String> names = Arrays.asList(this.getUserNames().split(","));
        this.userName = names;
    }

    public void setUserGroupId(){
        if (! StringUtils.isEmpty(this.getUserGroupIds()) ){
            List<String> groupIds = Arrays.asList(this.getUserGroupIds().split(","));
            List<Long> groupIdList = new ArrayList<>();
            for (String groupId:groupIds){
                Long id = Long.parseLong(groupId);
                groupIdList.add(id);
            }
            this.userGroupId = groupIdList;
        }
    }
}
