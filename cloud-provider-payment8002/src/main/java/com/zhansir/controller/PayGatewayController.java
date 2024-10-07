package com.zhansir.controller;

import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.entities.Pay;
import com.zhansir.common.resp.ResultData;
import com.zhansir.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PayGatewayController {
    @Resource
    public PayService payService;

    @GetMapping("/pay/gateway/get/{id}")
    public ResultData<PayDTO> getPayById(@PathVariable("id") Integer id) {
        log.info("start get id: {{}} data...", id);
        Pay pay = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay, payDTO);
        return ResultData.success(payDTO);
    }

    @GetMapping("/pay/gateway/getall")
    public ResultData<List<PayDTO>> getPayAll() {
        log.info("start get all data...");
        List<Pay> list = payService.list();
        List<PayDTO> res = list.stream().map(pay -> {
            PayDTO payDTO = new PayDTO();
            BeanUtils.copyProperties(pay, payDTO);
            return payDTO;
        }).toList();
        return ResultData.success(res);
    }
}
