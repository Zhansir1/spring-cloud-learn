package com.zhansir.controller;

import com.zhansir.common.resp.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${zhansir.env}")
    private String env;

    @GetMapping("/pay/config")
    public ResultData<String> payConfig(){
        return ResultData.success(env);
    }
}
