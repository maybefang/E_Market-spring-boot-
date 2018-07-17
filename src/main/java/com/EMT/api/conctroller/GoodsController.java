package com.EMT.api.conctroller;


import com.EMT.api.request.GoodsRequest;
import com.EMT.api.request.IdRequest;
import com.EMT.api.request.StrRequest;
import com.EMT.api.response.GoodsResponse;
import com.EMT.api.response.Response;
import com.EMT.api.response.Responses;
import com.EMT.domain.Model.Goods;
import com.EMT.domain.Service.GoodsService;
import com.EMT.domain.Service.ShopService;
import com.alibaba.fastjson.JSON;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping(value = "goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private ShopService shopService;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    //增加物品
    @PostMapping("/add")
    public Response addGoods(@Valid @RequestBody GoodsRequest goodsRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("添加物品失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }else {

            Goods goods = new Goods();
//            String answer = singleFileUpload(goodsRequest.getPic()).toString();
//            if(!answer.equals("文件为空，请重新上传") && !answer.equals("后端异常...")) {
//                goods.setGname(goodsRequest.getGname());
//                goods.setMsg(goodsRequest.getMsg());
//                goods.setNum(goodsRequest.getNum());
//                goods.setPic(answer);
//                goods.setPrice(goodsRequest.getPrice());
//                goods.setSid(goodsRequest.getSid());
//                goods.setSname(shopService.searchShopBySid(goodsRequest.getSid()));
//
//                Long addID = goodsService.addGoods(goods);
//                if (addID <= 0) {
//                    return Responses.errorResponse("添加物品信息失败");
//                }
//                Response response = Responses.successResponse();
//                HashMap<String, Object> data = new HashMap<>();
//                data.put("success", addID);
//                response.setData(data);
//                return response;
//            }else {
//                Response response = Responses.errorResponse(answer);
//                Map<String, Object> data = new HashMap<>();
//                data.put("errorMessage", bindingResult.getAllErrors());
//                response.setData(data);
//                return response;
//            }

            //没有文件传输
            goods.setGname(goodsRequest.getGname());
            goods.setMsg(goodsRequest.getMsg());
            goods.setNum(goodsRequest.getNum());
            goods.setPic(goodsRequest.getPic());
            goods.setPrice(goodsRequest.getPrice());
            goods.setSid(goodsRequest.getSid());
            goods.setSname(shopService.searchShopBySid(goodsRequest.getSid()));

            Long addID = goodsService.addGoods(goods);
            if (addID <= 0) {
                return Responses.errorResponse("添加物品信息失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", addID);
            response.setData(data);
            return response;

        }
    }

    //文件上传
    public Object singleFileUpload(MultipartFile file) {

        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空，请重新上传";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }

    //删除物品
    @PostMapping("/delete")
    public Response deleteGoods(@Valid @RequestBody IdRequest gid, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("删除物品失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        Long deleteID = goodsService.deleteGoods(gid.getId());
        if (deleteID <= 0) {
            return Responses.errorResponse("删除物品失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneGoods", deleteID);
        response.setData(data);
        return response;
    }

    //查找所有库存不为0的物品
    @PostMapping("/get_all")
    public Response goodsList(HttpServletRequest request) {

        List<Goods> goodsList = goodsService.getAllGoods();
        if(goodsList.size() == 0){
            return Responses.errorResponse("没有物品");
        }
        Response response = Responses.successResponse();

        HashMap<String,Object> data = new HashMap<>();
        data.put("List",goodsList);
        data.put("size",goodsList.size());

        response.setData(data);
        return response;
    }

    //根据名字查找物品
    @PostMapping(value = "/search_by_name")
    public Response findGoodsByGname(@Valid @RequestBody StrRequest lgname, BindingResult bindingResult) {
        //System.out.print(lgname.getGname() + "1111111111111111111111111111111");
        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("查找物品失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }
        List<Goods> goodsList = goodsService.searchByName(lgname.getStr());

        if (goodsList.size() == 0) {
            return Responses.errorResponse("没有相关物品");
        }
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("List", goodsList);
        data.put("size",goodsList.size());

        response.setData(data);
        return response;
    }

    //根据gid查物品所有信息
    @PostMapping("/detail")
    public Response findOne(@Valid @RequestBody IdRequest idRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("查看物品失败，验证错误");
            Map<String, Object> data = new HashMap<>();
            data.put("errorMessage", bindingResult.getAllErrors());
            response.setData(data);
            return response;
        }

        Goods goods = goodsService.oneGods(idRequest.getId());
        if (goods == null) {
            return Responses.errorResponse("物品不存在");
        }
        GoodsResponse goodsResponse = new GoodsResponse();
        goodsResponse.setGid(goods.getGid());
        goodsResponse.setGname(goods.getGname());
        goodsResponse.setMsg(goods.getMsg());
        goodsResponse.setNum(goods.getNum());
        goodsResponse.setPrice(goods.getPrice());
        goodsResponse.setPic(goods.getPic());
        goodsResponse.setSid(goods.getSid());
        goodsResponse.setSname(goods.getSname());

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", goodsResponse);
        response.setData(data);
        return response;
    }
}
