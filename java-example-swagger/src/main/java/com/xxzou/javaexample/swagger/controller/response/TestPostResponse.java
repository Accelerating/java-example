package com.xxzou.javaexample.swagger.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("testPost响应体")
public class TestPostResponse {

    @ApiModelProperty("testPost返回参数")
    private String testPostResponseValue;

    public String getTestPostResponseValue() {
        return testPostResponseValue;
    }

    public void setTestPostResponseValue(String testPostResponseValue) {
        this.testPostResponseValue = testPostResponseValue;
    }
}
