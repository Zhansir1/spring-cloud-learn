package com.zhansir.common.apis;

import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cloud-gateway")
public interface PayGatewayFeignApi {

    @GetMapping("/getPay/{id}")
    ResultData<PayDTO> getPay(@PathVariable("id") Integer id);

    @GetMapping("/getAllPay")
    ResultData<List<PayDTO>> getPayAll();

}
