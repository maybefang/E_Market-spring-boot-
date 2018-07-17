package com.EMT.api.conctroller;

import com.EMT.api.request.GoodsRequest;
import com.EMT.api.request.IdRequest;
import com.EMT.api.request.SetNumRequest;
import com.EMT.api.request.ShopRequest;
import com.EMT.api.response.Response;
import com.EMT.api.response.Responses;
import com.EMT.domain.Model.Goods;
import com.EMT.domain.Model.Shop;
import com.EMT.domain.Service.GoodsService;
import com.EMT.domain.Service.ShopService;
import com.EMT.domain.Service.ShopService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShopController {


    @Resource
    private ShopService shopService;

    //添加一个商店
    @PostMapping(value = "/shop_register")
    public Response addShop(@Valid @RequestBody ShopRequest shopRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("用户注册失败，验证错误！");
            Map<String,Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            //System.out.print("添加一个用户1");
            return response;
        } else {
            Shop shop = new Shop();
            shop.setSname(shopRequest.getSname());
            shop.setSpwd(shopRequest.getSpwd());
            //System.out.print("添加一个用户2");
            Long addId = shopService.addShop(shop);
            if(addId <=0) {
                return Responses.errorResponse("用户注册失败");
            }
            Response response = Responses.successResponse();
            HashMap<String,Object> data = new HashMap<>();
            data.put("success",addId);
            response.setData(data);
            //System.out.print("添加一个用户3");
            return response;
        }
    }

    //按店铺sid查找物品
    @PostMapping(value = "/shop_goods")
    public Response findGoodsBySid(@Valid @RequestBody IdRequest sid,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加购物车失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        List<Goods> goodsList = shopService.searchGoodsBySid(sid.getId());
        if (goodsList.size() == 0) {
            return Responses.errorResponse("没有物品发布");
        }
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("List", goodsList);
        data.put("size",goodsList.size());

        response.setData(data);
        return response;
    }
    //店铺设置库存
    @PostMapping(value = "shop_update_num")
    public Response updateNum(@Valid @RequestBody SetNumRequest setNumRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("修改失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }else {
            Long updateID = shopService.updateNum(setNumRequest.getNum(),setNumRequest.getGid());
            if (updateID <= 0) {
                return Responses.errorResponse("修改失败");
            }

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("updateNum", updateID);
            response.setData(data);

            return response;
        }
    }

    //店铺登录
    @PostMapping(value = "/shop_log_in")
    public Response logIn(@Valid @RequestBody ShopRequest shopRequest,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("店铺登录失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }else {
            Integer uid = shopService.shopLogIn(shopRequest.getSname(),shopRequest.getSpwd());

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("uid", uid);
            response.setData(data);
            return response;
        }
    }

}
