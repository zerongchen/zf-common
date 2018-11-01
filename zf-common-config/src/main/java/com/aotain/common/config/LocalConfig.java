package com.aotain.common.config;

import com.alibaba.fastjson.JSON;
import com.aotain.common.config.constant.ConfigRedisConstant;
import com.aotain.common.config.model.CommandSmms;
import com.aotain.common.config.model.ConnectionInformation;
import com.aotain.common.config.model.EuAttributeInfo;
import com.aotain.common.config.model.IdcHouses;
import com.aotain.common.config.redis.BaseRedisService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 获取配置信息相关类
 *
 * @author daiyh@aotain.com
 * @date 2017/11/15
 */
public class LocalConfig {

    private static LocalConfig localConfig = null;
    
    @SuppressWarnings("unchecked")
	private BaseRedisService<String, String, String> redisService = ContextUtil.getContext().getBean("baseRedisServiceImpl",BaseRedisService.class);

    private LocalConfig(){ //私有化构造器

    }

    public static LocalConfig getInstance() {
        if(localConfig==null){
            synchronized (LocalConfig.class) {
                if(localConfig==null){
                    localConfig =  new LocalConfig();
                }
            }
        }
        return localConfig;
    }

    /**
     * 根据KEY获得value
     * @param hashKey
     * @return
     */
    public String getHashValueByHashKey(String hashKey) {
        return redisService.getHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT, hashKey);
    }

    /**
     * 获得kafka消费配置
     * @return
     */
    public Map<String, Object> getKafkaCustomerConf(){
        Map<String, Object> conf = new HashMap<String, Object>();
        conf.put("zookeeper.connect", getHashValueByHashKey("zookeeper.connect") + getHashValueByHashKey("kafka.path"));
        conf.put("zookeeper.session.timeout.ms", getHashValueByHashKey("zookeeper.session.timeout.ms"));
        conf.put("zookeeper.sync.time.ms", getHashValueByHashKey("zookeeper.sync.time.ms"));
        conf.put("auto.commit.interval.ms", getHashValueByHashKey("auto.commit.interval.ms"));
        
        return conf;
    }

    /**
     * 获得kafka生产配置
     * @return
     */
    public Map<String, Object> getKafkaProducerConf(){
        Map<String, Object> conf = new HashMap<String, Object>();

        conf.put("metadata.broker.list", getHashValueByHashKey("metadata.broker.list"));

        return conf;
    }
    
    /**
     * 活跃机房编码对应的信息
     * @param houseIdStr	机房编码
     * @return
     */
    public IdcHouses getIdcHouse(String houseIdStr) {
    	String json = redisService.getHash("IdcHouses", houseIdStr);
        return JSON.parseObject(json, IdcHouses.class);
    }
    
    /**
     * 获取机房编码对应的机房信息
     * @param houseIdStr	机房编码
     * @param isReport	是否报备
     * @return
     */
    public IdcHouses getIdcHouse(String houseIdStr, boolean isReport) {
    	IdcHouses idcHouses = getIdcHouse(houseIdStr);
    	if (idcHouses != null) {
    		Integer reportFlag = idcHouses.getIsReport();
    		boolean rIsReport = (reportFlag == 1 ? false : true);
    		if (!(rIsReport == isReport)) {
    			idcHouses = null;
    		}
    	} 
        return idcHouses;
    }
    
    /**
     * CU代码遗留
     * 获取所有的已报备的机房信息
     * @return
     */
    public Map<String, IdcHouses> getAllIdcHouses() {
    	return getAllIdcHouses(true);
    }
    
    /**
     * CU代码遗留
     * 获取所有未报备或者已报备的机房信息
     * @param isReport 是否报备
     * @return
     */
    public Map<String, IdcHouses> getAllIdcHouses(Boolean isReport) {
    	Map<String, IdcHouses> result = new HashMap<String, IdcHouses>();
    	Map<String, String> temp = redisService.getHashs("IdcHouses");
    	Iterator<String> it = temp.keySet().iterator();
    	while (it.hasNext()) {
    		String houseIdStr = it.next();
    		String json = temp.get(houseIdStr);
    		
    		IdcHouses idcHouses = JSON.parseObject(json, IdcHouses.class);
    		if (idcHouses != null) {
    			Integer reportFlag = idcHouses.getIsReport();
    			boolean rIsReport = (reportFlag == 1 ? false : true);
    			if (isReport != null) {
    				if (rIsReport == isReport) {
        				result.put(houseIdStr, idcHouses);
            		}
    			} else {
    				result.put(houseIdStr, idcHouses);
    			}
    		}
    	}
		return result;
    }

    /**
     * CU代码遗留
     * 根据机房ID获得机房编码
     * @param houseId
     * @return
     */
    public String getHouseIdStrByHouseId(Long houseId){
        
        String houseIdStr = "";
        
        Map<String, IdcHouses> allHouse = getAllIdcHouses(null);
        
        for(Entry<String, IdcHouses> entry : allHouse.entrySet()){
            if(entry.getValue().getHouseId().equals(houseId)){
                houseIdStr = entry.getKey();
                break;
            }
        }
        
        return houseIdStr;
    }
    
    /**
     * CU代码遗留
     * 获取IdcId的值
     * @return
     */
	public String getIdcId() {
		String idcId = "A2.B1.B2-20090001";
		Map<String, IdcHouses> allHouse = getAllIdcHouses(null);
		Iterator<String> it = allHouse.keySet().iterator();
		while (it.hasNext()) {
			String houseIdStr = it.next();
			IdcHouses idcHouse = allHouse.get(houseIdStr);
			idcId = idcHouse.getIdcId();
			break;
		}
		return idcId;
	}
	
	/**
     * CU代码遗留
	 * 根据机房的编码获取机房的ID，长整型值
	 * @param houseIdStr
	 * @return
	 */
	public Long getHouseIdByHouseIdStr(String houseIdStr){
        Long houseId = 0L;
        IdcHouses idcHouse = getIdcHouse(houseIdStr);
        return (idcHouse == null ? houseId : idcHouse.getHouseId());
    }
    
	/**
     * CU代码遗留 需要修改
     * 获取Eu Ip信息
     * @return
     */
    public Map<String, EuAttributeInfo> getAllEuAttributeInfo() {
    	Map<String, EuAttributeInfo> result = new HashMap<String, EuAttributeInfo>();
    	Map<String, String> temp = redisService.getHashs("dpi_attribute_info");
    	Iterator<String> it = temp.keySet().iterator();
    	while (it.hasNext()) {
    		String euip = it.next();
    		String json = temp.get(euip);
    		
    		result.put(euip, JSON.parseObject(json, EuAttributeInfo.class));
    	}
		return result;
    }
    /**
     * CU代码遗留 需要修改
     * 根据ip获取对应eu信息
     * @param ip
     * @return
     */
    public EuAttributeInfo getEuAttributeInfoByEuip(String ip){ 
	    String json = redisService.getHash("dpi_attribute_info", ip);
	    return JSON.parseObject(json, EuAttributeInfo.class);
    }
    /**
     * CU代码遗留
     * 根据ip获取对应eu软件厂商
     * @param ip
     * @return
     */
    public String getSoftwareProviderByEuip(String ip){
    	EuAttributeInfo info = getEuAttributeInfoByEuip(ip);
    	return (info == null ? "" : info.getSoftwareProvider());
    }
    
    
    /**
     * CU代码遗留
     * 获取Eu Ip信息
     * @return
     */
    public Map<String, CommandSmms> getAllCommandSmms() {
    	Map<String, CommandSmms> result = new HashMap<String, CommandSmms>();
    	Map<String, String> temp = redisService.getHashs("CommandSmms");
    	Iterator<String> it = temp.keySet().iterator();
    	while (it.hasNext()) {
    		String commandid = it.next();
    		String json = temp.get(commandid);
    		
    		result.put(commandid, JSON.parseObject(json, CommandSmms.class));
    	}
		return result;
    }
    /**
     * CU代码遗留
     * 根据ip获取对应eu信息
     * @return
     */
    public CommandSmms getCommandSmmsByCommandid(String commandid){ 
	    String json = redisService.getHash("CommandSmms", commandid);
	    return JSON.parseObject(json, CommandSmms.class);
    }
    /**
     * CU代码遗留
     * 根据commandid获取对应toptaskid
     * @param commandid
     * @return
     */
    public long getToptaskidByCommandid(String commandid){
    	CommandSmms info = getCommandSmmsByCommandid(commandid);
    	return (info == null ? 0l : info.getToptaskid());
    }
    
    public ConnectionInformation getConnectionInformation(String hashField) {
    	String json = redisService.getHash("ClusterHouseConfiguration", hashField);
        return JSON.parseObject(json, ConnectionInformation.class);
    }
    
    /**
     * CU代码遗留
     * 根据机房的编码获取Azkaban的url地址
     * @param houseIdStr 机房编码
     * @return
     */
    public String getAzkabanUrlByHouseIdStr(String houseIdStr){
        IdcHouses idcHouse = getIdcHouse(houseIdStr);
        String json = redisService.getHash("ClusterHouseConfiguration", idcHouse.getClusterId() + "");
        ConnectionInformation info = JSON.parseObject(json, ConnectionInformation.class);
        return info.getAzkabanUrl();
    }
    
    /**
     * CU代码遗留
     * 根据机房的编码获取机房集群的namenode信息
     * @param houseIdStr 机房编码
     * @return
     */
    public String getNameNodeInfoByHouseIdStr(String houseIdStr){
        IdcHouses idcHouse = getIdcHouse(houseIdStr);
        String json = redisService.getHash("ClusterHouseConfiguration", idcHouse.getClusterId() + "");
        ConnectionInformation info = JSON.parseObject(json, ConnectionInformation.class);
        return info.getNamenodeInfo();
    }

    public String getEventItemFilePath(){
        String json = redisService.getHash("idc_jdcm_jkcs_config","eventitem_file_path");
        return json;
    }
    
    /**
     * 更新IdcHouses
     */
    public void updateIdcHouse(IdcHouses idcHouse) {
    	if(idcHouse != null && idcHouse.getHouseIdStr() != null){
    		redisService.putHash("IdcHouses", idcHouse.getHouseIdStr(),JSON.toJSONString(idcHouse));
    	}
    }
}
