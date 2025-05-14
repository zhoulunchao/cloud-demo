package cn.itcast.order;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    //创建RestTemplate对象并注入spring容器
    @Bean
    //(如果有一个服务，多个端口，这个这个注解可以实现负载均衡)
    //==================================分支测试====================
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    //配置负载均衡规则
//    @Bean
//    public IRule randomRule(){
//        return new RandomRule();
//    }


}