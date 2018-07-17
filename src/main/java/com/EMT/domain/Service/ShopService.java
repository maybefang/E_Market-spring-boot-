package com.EMT.domain.Service;

import com.EMT.domain.Model.Goods;
import com.EMT.domain.Model.Shop;
import com.EMT.interf.mapper.GoodsMapper;
import com.EMT.interf.mapper.ShopMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private GoodsMapper goodsMapper;

    //添加一个店铺
    public Long addShop(Shop shop) {
        return shopMapper.insertShop(shop);
    }

    //根据sid查找店铺
    public String searchShopBySid(Integer sid) {
        return shopMapper.searchBySid(sid);
    }

    //更改商品库存
    public Long updateNum(Integer num,Integer gid) {
        return shopMapper.updatenum(num,gid);
    }

    //搜索本店物品
    public List<Goods> searchGoodsBySid(Integer sid) {
        return goodsMapper.searchBySid(sid);
    }

    //查找店铺（登录）
    public Integer shopLogIn(String sname,String spwd) {
        return shopMapper.shopLogIn(sname,spwd);
    }
}
