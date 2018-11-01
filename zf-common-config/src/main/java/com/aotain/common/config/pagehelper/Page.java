package com.aotain.common.config.pagehelper;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公共分页对象
 *
 * @author daiyh@aotain.com
 * @date 2018/02/28
 */
@Getter
@Setter
public class Page<T> {
    /**
     * 页码，默认是第一页
     */
    private int page = 1;
    /**
     * 每页显示的记录数，默认是10
     */
    private int pageSize = 10;
    /**
     * 总记录数
     */
    private int totalRecord;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 对应的当前页记录
     */
    private List<T> results;

    /**   just for appUser time search     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date searchEndTime;
    /**   just for appUser time search     */

    /**
     * 其他的参数我们把它分装成一个Map对象
     */
    private Map<String, Object> params = Maps.newHashMap();
    /**
     * 模糊查询参数
     */
    private Map<String,Object> fuzzyParams = Maps.newHashMap();

    /**
     * 模糊查询参数
     */
    private Map<String,Object> notEqualOperateParams = Maps.newHashMap();

    /**
     * 时间范围参数
     */
    private Map<String,TimeScope> timeScopeParams = Maps.newHashMap();

    private Map<String,Object> orderByParams = Maps.newHashMap();

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号
        int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
        this.setTotalPage(totalPage);
    }

}
