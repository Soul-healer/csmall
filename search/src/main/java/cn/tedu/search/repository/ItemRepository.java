package cn.tedu.search.repository;

import cn.tedu.search.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    Iterable<Item> queryItemsByTitleMatches(String title);

    //多条件查询
    Iterable<Item> queryItemsByTitleMatchesAndBrandMatches(String title,String brand);

    //排序查询
    Iterable<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title,String brand);
    //分页查询
    Page<Item> queryItemsByTitleMatchesOrBrandMatchesOrderByPriceDesc(String title, String brand, Pageable pageable);
}
