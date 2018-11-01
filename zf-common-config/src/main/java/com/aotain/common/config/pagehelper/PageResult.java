package com.aotain.common.config.pagehelper;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据返回结果
 *
 * @author daiyh@aotain.com
 * @date 2018/02/28
 */
@Getter
@Setter
public class PageResult {

    @NonNull
    private Integer status;

    @NonNull
    private String message;

    /**
     * 返回的数据内容
     */
    private List rows = new ArrayList();

    /**
     * 记录总条数
     */
    private Long total;

    public PageResult(){

    }

    public PageResult(long total,List rows){
        this.total = total;
        this.rows = rows;
    }

    public PageResult(long total,List rows,int status,String message){
        this.total = total;
        this.rows = rows;
        this.status = status;
        this.message = message;
    }

    public static PageResult getErrorPageResult(String message){
        PageResult pageResult = new PageResult(0,null,-1,message);
        return pageResult;
    }
}
