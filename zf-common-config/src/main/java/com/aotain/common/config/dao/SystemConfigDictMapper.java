package com.aotain.common.config.dao;

import com.aotain.common.config.annotation.MyBatisDao;
import com.aotain.common.config.model.SystemConfigDict;

import java.util.List;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/02/06
 */
@MyBatisDao
public interface SystemConfigDictMapper {

    List<SystemConfigDict> selectConfig();
}
