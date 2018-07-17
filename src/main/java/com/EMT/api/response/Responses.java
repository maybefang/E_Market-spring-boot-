package com.EMT.api.response;

import java.util.HashMap;

public class Responses {

    /**
     * build success response.
     * @return response
     */
    public static Response successResponse() {
        Response response = new Response();
        RespMeta respMeta = new RespMeta();
        respMeta.setCode(0);
        response.setMeta(respMeta);

        return response;
    }


    /**
     * build success response
     */
    public static Response successResponse(HashMap data) {
        Response response = new Response();

        RespMeta respMeta = new RespMeta();
        respMeta.setCode(0);
        response.setMeta(respMeta);

        response.setData(data);
        return response;
    }


    /**
     * build error response
     */
    public static Response errorResponse(String errorMsg) {

        RespMeta respMeta = new RespMeta();
        respMeta.setCode(1);
        respMeta.setErrorMsg(errorMsg);

        Response response = new Response();
        response.setMeta(respMeta);
        return response;
    }
}
