package com.EMT.domain.Service;

import com.EMT.api.request.GlistRequest;
import com.EMT.domain.Model.Glist;
import com.EMT.domain.Model.Goods;
import com.EMT.interf.mapper.GlistMapper;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GlistService {
    @Resource
    private GlistMapper glistMapper;

    //向购物车中增加商品
    public Long addGlist(Glist glist) {
        return glistMapper.insertGlist(glist);
    }

    //购物车里删除物品
    public Long deleteGlist(Integer glid) {
        return glistMapper.deletGlist(glid);
    }

    //从购物车里买东西
    public Long buy(Integer glid) {
        return glistMapper.buyInGlist(glid);
    }

    //显示购物车里的物品
    public List<Goods> goodsInList(Integer uid) {
        return glistMapper.goodsInGlist(uid);
    }

    //直接购买
    public Long buyDirect(GlistRequest glist) { return glistMapper.buyDirect(glist);}
}
