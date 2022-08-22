package cn.tedu.csmall.cart.service;

import cn.tedu.csmall.commons.pojo.cart.dto.CartAddDTO;

public interface ICartService {
    //新增购物车商品
    void cartAdd(CartAddDTO cartAddDTO);

    //删除购物车
    void deleteUserCart(String userId,String commodityCode);
}
