package com.xxzou.javaexample.swagger.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("testGet响应体")
public class TestGetResponse {

    @ApiModelProperty("getParam参数")
    public String getParam;

    public String getGetParam() {
        return getParam;
    }

    public void setGetParam(String getParam) {
        this.getParam = getParam;
    }
}
