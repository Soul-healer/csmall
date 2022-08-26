package cn.tedu.search;

import cn.tedu.search.entity.Item;
import cn.tedu.search.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.elasticsearch.core.query.Query.findAll;

@SpringBootTest
public class SpringEsTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addOne(){
        Item item=new Item()
                .setId(1L)
                .setTitle("罗技激光无线游戏鼠标")
                .setCategory("鼠标")
                .setBrand("罗技")
                .setPrice(168.0)
                .setImgPath("/1.jpg");
        itemRepository.save(item);
        System.out.println("ok");
    }

    @Test
    void getOne(){
        Optional<Item> optional=itemRepository.findById(1L);
        Item item= optional.get();
        System.out.println(item);
    }
    //批量增
    @Test
    void addList(){
        List<Item> list=new ArrayList<>();
        list.add(new Item(2L,"罗技激光有线办公鼠标","鼠标","罗技",88.0,"/2.jpg"));
        list.add(new Item(3L,"雷蛇机械无线游戏键盘","键盘","雷蛇",299.0,"/3.jpg"));
        list.add(new Item(4L,"微软有线静音办公鼠标","鼠标","微软",205.0,"/4.jpg"));
        list.add(new Item(5L,"罗技机械有线背光键盘","键盘","罗技",268.0,"/5.jpg"));
        itemRepository.saveAll(list);
        System.out.println("ok list");
    }
    //全查
    @Test
    void getAll(){
        Iterable<Item> items=itemRepository.findAll();
        for (Item item:items){
            System.out.println(item);
        }
        items.forEach(item -> System.out.println(item));
    }

    //单条件查询
    @Test
    void queryOne(){
        Iterable<Item> items=itemRepository.queryItemsByTitleMatches("游戏");
        items.forEach(item -> System.out.println(item));
    }

    //多条件查询
    @Test
    void queryTwo() {
        Iterable<Item> items = itemRepository.queryItemsByTitleMatchesAndBrandMatches("游戏", "罗技");
        items.forEach(item -> System.out.println(item));
    }
    //排序查询
    @Test
    void queryOrder(){
        Iterable<Item> items=itemRepository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏", "罗技");
        items.forEach(item -> System.out.println(item));
    }

    //分页查询
    @Test
    void queryPage(){
        int pageNum=1;  //要查询的页码
        int pageSize=2;  //每页包含的数据条数
        Page<Item> itemPage=itemRepository.queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc("游戏", "罗技", PageRequest.of(pageNum-1,pageSize));
        itemPage.forEach(item -> System.out.println(item));
        //page对象中包含的分页信息
        System.out.println("总页数:"+ itemPage.getTotalPages());
        System.out.println("总条数:"+ itemPage.getTotalElements());
        System.out.println("当前页:"+(itemPage.getNumber()+1));
        System.out.println("每页条数:"+itemPage.getSize());
        System.out.println("是否为首页:"+itemPage.isFirst());
        System.out.println("是否为末页:"+itemPage.isLast());
    }











}
