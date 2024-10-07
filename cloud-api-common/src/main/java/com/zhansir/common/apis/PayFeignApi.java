package com.zhansir.common.apis;

import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cloud-provider-payment")
public interface PayFeignApi {

    @PostMapping("/pay/insert")
    ResultData<String> insert(@RequestBody PayDTO dto);

    @DeleteMapping("/pay/delete")
    ResultData<String> delete(@RequestParam("id") Integer id);

    @PostMapping("/pay/update")
    ResultData<String> update(@RequestBody PayDTO dto);

    @GetMapping("/pay/get/{id}")
    ResultData<PayDTO> get(@PathVariable("id") Integer id);

    @GetMapping("/pay/getAll")
    ResultData<List<PayDTO>> getAll();

    @GetMapping("/pay/circuit/{id}")
    ResultData<String> circuitTest(@PathVariable("id") Integer id);

    @GetMapping("/pay/rateLimit/{id}")
    ResultData<String> rateTest(@PathVariable("id") Integer id);

}
