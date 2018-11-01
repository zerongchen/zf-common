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
 * @Date : 2018年7月20日 上午9:24:40
 */
package com.aotain.common.utils.zookeeper;

import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

public class ServiceRegistry {

    private ServiceDiscovery<InstanceDetails> serviceDiscovery;
    private final CuratorFramework client;

    public ServiceRegistry(CuratorFramework client, String basePath) throws Exception {
        this.client = client;
        JsonInstanceSerializer<InstanceDetails> serializer = new JsonInstanceSerializer<InstanceDetails>(
                InstanceDetails.class);
        serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class).client(client).serializer(serializer)
                .basePath(basePath).build();
        serviceDiscovery.start();
    }

    public void registerService(ServiceInstance<InstanceDetails> serviceInstance) throws Exception {
        serviceDiscovery.registerService(serviceInstance);
    }

    public void unregisterService(ServiceInstance<InstanceDetails> serviceInstance) throws Exception {
        serviceDiscovery.unregisterService(serviceInstance);

    }

    public void updateService(ServiceInstance<InstanceDetails> serviceInstance) throws Exception {
        serviceDiscovery.updateService(serviceInstance);

    }

    public void close() throws IOException {
        serviceDiscovery.close();
    }
    
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.50.202:2181", new ExponentialBackoffRetry(30000, 3));
        client.start();
        ServiceRegistry serviceRegistrar = new ServiceRegistry(client,"zf_log");
        ServiceInstance<InstanceDetails> instance1 = ServiceInstance.<InstanceDetails>builder()
                .name("service1")
                .port(12345)
                .address("192.168.1.100")   //address不写的话，会取本地ip
                .payload(new InstanceDetails(1l,"192.168.1.100","Test.Service1"))
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .build();
        serviceRegistrar.registerService(instance1);
        
        ServiceInstance<InstanceDetails> instance2 = ServiceInstance.<InstanceDetails>builder()
                .name("service1")
                .port(12345)
                .address("192.168.1.101")
                .payload(new InstanceDetails(2l,"192.168.1.101","Test.Service2"))
                .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                .build();
        serviceRegistrar.registerService(instance2);


        Thread.sleep(Integer.MAX_VALUE);
    }
}