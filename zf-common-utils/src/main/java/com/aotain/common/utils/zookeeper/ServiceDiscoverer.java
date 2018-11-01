/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 
 * ---------------------------------
 * @Author : kuangbin
 * @Date : 2018年7月20日 上午9:25:04
 */
package com.aotain.common.utils.zookeeper;

import java.io.Closeable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RandomStrategy;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ServiceDiscoverer {
    private ServiceDiscovery<InstanceDetails> serviceDiscovery;
    private Map<String, ServiceProvider<InstanceDetails>> providers = Maps.newHashMap();
    private List<Closeable> closeableList = Lists.newArrayList();
    private Object lock = new Object();

    public ServiceDiscoverer(CuratorFramework client, String basePath) throws Exception {
        JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<InstanceDetails>(
                InstanceDetails.class);
        serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class).client(client).basePath(basePath)
                .serializer(serializer).build();

        serviceDiscovery.start();
    }

    public ServiceInstance<InstanceDetails> getInstanceByName(String serviceName) throws Exception {
        ServiceProvider<InstanceDetails> provider = providers.get(serviceName);
        if (provider == null) {
            synchronized (lock) {
                provider = providers.get(serviceName);
                if (provider == null) {
                    provider = serviceDiscovery.serviceProviderBuilder().serviceName(serviceName)
                            .providerStrategy(new RandomStrategy<InstanceDetails>()).build();
                    provider.start();
                    closeableList.add(provider);
                    providers.put(serviceName, provider);
                }
            }
        }

        return provider.getInstance();
    }
    
    public Collection<ServiceInstance<InstanceDetails>> listInstanceByName(String serviceName) throws Exception {
    	Collection<ServiceInstance<InstanceDetails>> instances = serviceDiscovery.queryForInstances("serviceName");
    	return instances;
    }

    public synchronized void close() {
        for (Closeable closeable : closeableList) {
            CloseableUtils.closeQuietly(closeable);
        }
    }
    
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.50.202:2181", new ExponentialBackoffRetry(30000, 3));
        client.start();
        
        ServiceDiscoverer serviceDiscoverer = new ServiceDiscoverer(client,"zf_log");
        Collection<ServiceInstance<InstanceDetails>> instances = serviceDiscoverer.listInstanceByName("service1");

        for ( ServiceInstance<InstanceDetails> instance : instances ){
        	System.out.println(instance.buildUriSpec());
            System.out.println(instance.getPayload());
        }
        serviceDiscoverer.close();
        /*
        ServiceDiscoverer serviceDiscoverer = new ServiceDiscoverer(client,"zf_log");

        ServiceInstance<InstanceDetails> instance1 = serviceDiscoverer.getInstanceByName("service1");
        
        System.out.println(instance1.buildUriSpec());
        System.out.println(instance1.getPayload());

        serviceDiscoverer.close();
        */
        CloseableUtils.closeQuietly(client);
    }

}
