package com.EMT.domain.Service;

import com.EMT.domain.Model.Goods;
import com.EMT.interf.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    //添加物品（店铺）
    public Long addGoods(Goods goods){
        return goodsMapper.insertGoods(goods);
    }

    //删除物品
    public  Long deleteGoods(Integer gid) {
        return goodsMapper.deleteGoods(gid);
    }

    //从购物车买入更新库存
    public Long updateGoods(Integer gid){
        return goodsMapper.updateGoodsNum(gid);
    }

    //查询所有物品显示到主页
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    //根据名字查物品
    public List<Goods> searchByName(String lgname){
        return goodsMapper.searchByGname(lgname);
    }

    //查找所有完成的订单
    public List<Goods> hasBuy(Integer uid) {
        return goodsMapper.hasBuy(uid);
    }

    //根据gid分查询物品
    public Goods oneGods(Integer gid) {
        return  goodsMapper.oneGoods(gid);
    }
}
