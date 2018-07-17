package com.EMT.api.conctroller;

import com.EMT.api.request.IdRequest;
import com.EMT.api.request.UserRequest;
import com.EMT.api.response.Response;
import com.EMT.api.response.Responses;

import com.EMT.domain.Model.Goods;
import com.EMT.domain.Model.User;
import com.EMT.domain.Service.GoodsService;
import com.EMT.domain.Service.UserService;

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

public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private GoodsService goodsService;

    //添加一个用户
    @PostMapping(value = "/register")
    public Response addUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("用户注册失败，验证错误！");
            Map<String,Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            //System.out.print("添加一个用户1");
            return response;
        } else {
            User user = new User();
            user.setUname(userRequest.getUname());
            user.setUpwd(userRequest.getUpwd());
            //System.out.print("添加一个用户2");
            Long addId = userService.addUser(user);
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

    //查看用户已完成订单
    @PostMapping(value = "/has_buy")
    public Response hasBuy(@Valid @RequestBody IdRequest uid,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("查看订单失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        List<Goods> goodsList = goodsService.hasBuy(uid.getId());
        User user = userService.searchUserByUid(uid.getId());

        if(goodsList.size() == 0) {
            return Responses.errorResponse("没有购买物品");
        }

        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("user",user);
        data.put("List", goodsList);
        data.put("size", goodsList.size());

        response.setData(data);
        return response;
    }

    //查找用户（用户登录）
    @PostMapping(value = "/log_in")
    public Response logIn(@Valid @RequestBody UserRequest userRequest,BindingResult bindingResult) {
        //System.out.print(userRequest.getUname() + userRequest.getUpwd());
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("用户登录失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }else {
            Integer uid = userService.logIn(userRequest.getUname(),userRequest.getUpwd());


            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("uid", uid);
            response.setData(data);
            return response;
        }
    }
}
