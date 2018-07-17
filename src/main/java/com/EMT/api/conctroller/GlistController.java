package com.EMT.api.conctroller;

import com.EMT.api.request.GlistRequest;
import com.EMT.api.request.IdRequest;
import com.EMT.api.response.Response;
import com.EMT.api.response.Responses;
import com.EMT.domain.Model.Glist;
import com.EMT.domain.Model.Goods;
import com.EMT.domain.Service.GlistService;
import com.EMT.domain.Service.GoodsService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "glist")
public class GlistController {

    @Resource
    private GlistService  glistService;

    @Resource
    private GoodsService goodsService;

    //加入购物车一条信息
    @PostMapping(value = "/add")
    public Response addGlist(@Valid @RequestBody GlistRequest glistRequest,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加购物车失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
            Glist glist = new Glist();
            System.out.print("gid: " + glistRequest.getGid() + "uid: " + glistRequest.getUid());

            glist.setGid(glistRequest.getGid());
            glist.setUid(glistRequest.getUid());

            Long addId = glistService.addGlist(glist);
            if (addId <= 0) {
                return Responses.errorResponse("添加购物车失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", addId);
            response.setData(data);
            return response;
        }
    }

    //直接购买
    @PostMapping(value = "/buy_direct")
    public Response buyDirect(@Valid @RequestBody GlistRequest glistRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("购买失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        } else {
//            Glist glist = new Glist();
//            //System.out.print("gid: " + glistRequest.getGid() + "uid: " + glistRequest.getUid());
//
//            glist.setGid(glistRequest.getGid());
//            glist.setUid(glistRequest.getUid());
            Long addId = glistService.buyDirect(glistRequest);
            Long buyD = goodsService.updateGoods(glistRequest.getGid());
            if (addId <= 0|| buyD<=0) {
                return Responses.errorResponse("购买失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", addId);
            response.setData(data);
            return response;
        }
    }

    //删除购物车中东西
    @PostMapping(value = "/delete")
    public Response deleteGlist(@Valid @RequestBody IdRequest glid,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("删除物品失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long deleteID = glistService.deleteGlist(glid.getId());
        if (deleteID <= 0) {
            return Responses.errorResponse("删除物品失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneGoods", deleteID);
        response.setData(data);
        return response;
    }

    //按照用户查找其购物车中的内容
    @PostMapping(value = "/search_all")
    public Response goodsInGlist(@Valid @RequestBody IdRequest uid,BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("购物车显示失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        List<Goods> goodsList = glistService.goodsInList(uid.getId());
        if(goodsList.size() == 0) {
            return Responses.errorResponse("购物车中没有物品");
        }else  {
            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("List",goodsList);
            data.put("size",goodsList.size());

            response.setData(data);
            return response;
        }
    }

    //购物车中买入购物车需要更新flag，并减少库存
    @PostMapping("/buy")
    public Response glistUpdate(@Valid @RequestBody IdRequest glid,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("购买失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }else {
            Long updateId = glistService.buy(glid.getId());
            Long update = goodsService.updateGoods(glid.getId());
            if(updateId <= 0) {
                return Responses.errorResponse("购买失败");
            }
            Response response = Responses.successResponse();

            HashMap<String,Object> data = new HashMap<>();
            data.put("updateGlid",updateId);
            data.put("updateGid",update);
            response.setData(data);

            return response;
        }
    }

}
