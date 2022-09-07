package cn.tedu.csmall.cart.webapi.kafka;

import cn.tedu.csmall.commons.pojo.cart.model.Cart;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// 需要接收kafka的消息,因为kafkaTemple是Spring容器管理的对象
// 所以消息的接收功能,也要保存到Spring容器中
@Component
public class Consumer {

    // SpringKafka接收消息依靠框架提供的"监听机制"
    // 框架中有一个线程,一直实时关注Kafka的消息接收
    // 如果我们指定的话题名称(myCart)接收了消息,那么这条线程就会自动调用下面的方法
    @KafkaListener(topics = "myCart")
    public void received(ConsumerRecord<String,String> record){
        String json= record.value();
        Gson gson=new Gson();
        Cart cart=gson.fromJson(json,Cart.class);
        System.out.println(cart);
    }




}
