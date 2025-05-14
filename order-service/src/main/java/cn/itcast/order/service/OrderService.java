package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;


    //跨服务远程调用数据（硬编码《不能把代码写死了》）
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用restTemplate发起http请求,查询用户
        // 2.1.获取url
        //硬编码，错误的写法
//        String url = "http://localhost:8081/user/" + order.getUserId();
        //使用注册服务与发现（eureka）之后直接调用服务
        String url = "http://user-server/user/" + order.getUserId();
        // 2.2.发起http请求,实现远程调用获取用户id
        User user = restTemplate.getForObject(url, User.class);
        // 3.设置用户信息
        order.setUser(user);
        // 4.返回
        return order;
    }
}
