package cn.tedu.csmall.cart.webapi.kafka;

import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import com.google.gson.Gson;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 这个类实现周期性向Kafka发送消息
// 因为Spring的任务调度需要将当类保存到Spring容器,所以要加下面的注解
@Component
public class Producer {

    // 直接获取Spring容器中支持向Kafka发送消息的对象
    // 这个对象会在SpringBoot启动时自动的装配到Spring容器
    // KafkaTemplate<[话题的类型],[消息的类型]>
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    // 实现每隔10秒向Kafka发送消息
    int i=1;
    // 设置每隔10秒(10000毫秒)运行一次的调度注解
    @Scheduled(fixedRate = 10000)
    // SpringBoot启动后,每隔10秒运行一次下面的方法
    public void sendMessage(){
        // 实例化一个Cart对象,赋值并发送给Kafka
        Cart cart=new Cart();
        cart.setId(i++);
        cart.setCommodityCode("PC100");
        cart.setUserId("UU100");
        cart.setPrice(RandomUtils.nextInt(90)+10);
        cart.setCount(RandomUtils.nextInt(10)+1);
        // "{"id":"1","price":"58",...}"
        // 利用Gson依赖,将cart对象转换为上面样式的json格式字符串
        Gson gson=new Gson();
        String json=gson.toJson(cart);
        System.out.println("要发送的信息为:"+json);
        // 执行发送
        kafkaTemplate.send("myCart",json);
    }


}
