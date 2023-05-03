package com.xxzou.javaexample.swagger.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("testPostRequest")
public class TestPostRequest {

    @ApiModelProperty("postParam1")
    private String postParam1;

    @ApiModelProperty("postParam2")
    private Integer postParam2;

    public String getPostParam1() {
        return postParam1;
    }

    public void setPostParam1(String postParam1) {
        this.postParam1 = postParam1;
    }

    public Integer getPostParam2() {
        return postParam2;
    }

    public void setPostParam2(Integer postParam2) {
        this.postParam2 = postParam2;
    }
}
