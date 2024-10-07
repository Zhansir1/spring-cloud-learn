package com.zhansir.controller;

import com.zhansir.common.apis.PayGatewayFeignApi;
import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PayGatewayController {

    @Resource
    public PayGatewayFeignApi payGatewayFeignApi;

    @GetMapping("/getPay/consumer/{id}")
    public ResultData<PayDTO> payGatewayGet(@PathVariable("id") Integer id) {
        return payGatewayFeignApi.getPay(id);
    }

    @GetMapping("/getAllPay/consumer")
    public ResultData<List<PayDTO>> getPayAll() {
        return payGatewayFeignApi.getPayAll();
    }

}
