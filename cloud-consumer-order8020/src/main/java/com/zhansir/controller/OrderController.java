package com.zhansir.controller;

import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController
{

    public static final String paymentUrl = "http://cloud-provider-payment";

    @Resource
    private RestTemplate commonRestTemplate;

    @PostMapping("/consumer/pay/insert")
    public ResultData<String> addOrder(@RequestBody PayDTO payDTO) {
        // 使用 ParameterizedTypeReference 来指定返回类型
        ParameterizedTypeReference<ResultData<String>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<ResultData<String>> responseEntity = commonRestTemplate.exchange(
                paymentUrl + "/pay/insert",
                HttpMethod.POST,
                new HttpEntity<>(payDTO),
                responseType
        );
        return responseEntity.getBody();
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData<PayDTO> getOrder(@PathVariable("id") Integer id) {
        String url = paymentUrl + "/pay/get/" + id;
        // 使用 ParameterizedTypeReference 来指定返回类型
        ParameterizedTypeReference<ResultData<PayDTO>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<ResultData<PayDTO>> responseEntity = commonRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                responseType
        );
        ResultData<PayDTO> responseEntityBody = responseEntity.getBody();
        if (responseEntityBody.getData() != null) {
            return ResultData.success(responseEntityBody.getData());
        }
        return ResultData.success(null);

    }

    @PostMapping("/consumer/pay/update")
    public ResultData<String> updateOrder(@RequestBody PayDTO payDTO) {
        ParameterizedTypeReference<ResultData<String>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<ResultData<String>> responseEntity = commonRestTemplate.exchange(
                paymentUrl + "/pay/update",
                HttpMethod.POST,
                new HttpEntity<>(payDTO),
                responseType
        );
        return responseEntity.getBody();
    }

}
