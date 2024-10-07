package com.zhansir.controller;

import com.zhansir.common.apis.PayFeignApi;
import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController
{

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/consumer/pay/insert")
    public ResultData<String> addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.insert(payDTO);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData<PayDTO> getOrder(@PathVariable("id") Integer id) {
        ResultData<PayDTO> responseEntityBody = payFeignApi.get(id);
        if (responseEntityBody.getData() != null) {
            return ResultData.success(responseEntityBody.getData());
        }
        return ResultData.success(null);
    }

    @PostMapping("/consumer/pay/update")
    public ResultData<String> updateOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.update(payDTO);
    }

}
