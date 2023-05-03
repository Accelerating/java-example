package com.xxzou.javaexample.swagger.controller;

import com.xxzou.javaexample.swagger.controller.request.TestPostRequest;
import com.xxzou.javaexample.swagger.controller.response.TestGetResponse;
import com.xxzou.javaexample.swagger.controller.response.TestPostResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "test-controller-tag", value = "test-controller-value")
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/get")
    @ApiOperation(value ="test get value",notes = "test get note", response = TestGetResponse.class)
    public TestGetResponse get(@ApiParam(name = "param", value = "param参数") String param){
        return new TestGetResponse();
    }

    @PostMapping("/post")
    @ApiOperation(value ="test post value",notes = "test post note", response = TestPostResponse.class)
    public TestPostResponse post(@RequestBody TestPostRequest request){
        return new TestPostResponse();
    }

}
