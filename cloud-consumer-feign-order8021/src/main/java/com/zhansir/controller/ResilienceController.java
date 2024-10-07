package com.zhansir.controller;

import com.zhansir.common.apis.PayFeignApi;
import com.zhansir.common.resp.ResultData;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResilienceController {

    private static final Logger log = LoggerFactory.getLogger(ResilienceController.class);
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/consumer/circuit/{id}")
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "circuitFallbackHandler")
    public ResultData<String> circuit(@PathVariable("id") Integer id) {
        return payFeignApi.circuitTest(id);
    }

    @GetMapping("/consumer/rateLimit/{id}")
    @RateLimiter(name = "cloud-provider-payment", fallbackMethod = "rateLimitFallbackHandler")
    public ResultData<String> rateTest(@PathVariable("id") Integer id) {
        return payFeignApi.rateTest(id);
    }

    public ResultData<String> circuitFallbackHandler(Integer id, Throwable throwable) {
        return ResultData.success("远程服务错误,请稍后再试");
    }

    public ResultData<String> rateLimitFallbackHandler(Integer id, Throwable throwable) {
        return ResultData.success("访问速度过快,请稍后再试");
    }

}
