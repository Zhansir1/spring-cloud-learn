package com.zhansir.controller;

import com.zhansir.common.dto.PayDTO;
import com.zhansir.common.entities.Pay;
import com.zhansir.common.excep.ZhanExceptionType;
import com.zhansir.common.excep.ZhanRuntimeException;
import com.zhansir.common.resp.ResultData;
import com.zhansir.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/pay/insert")
    @Operation(summary = "插入支付记录", description = "传入一个完整的pay实体，然后新增一条记录")
    public ResultData<String> insert(@RequestBody PayDTO dto) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(dto, pay);
        payService.save(pay);
        return ResultData.success("success");
    }

    @DeleteMapping("/pay/delete")
    @Operation(summary = "删除支付记录", description = "传入一个id，然后根据id删除一条记录")
    public ResultData<String> delete(@RequestParam Integer id) {
        Pay pay = payService.getById(id);
        pay.setDeleted(1);
        payService.updateById(pay);
        return ResultData.success("success");
    }

    @PostMapping("/pay/update")
    @Operation(summary = "更新支付记录", description = "传入一个完整的payDTO实体，然后更新一条记录，不存在则插入一条记录")
    public ResultData<String> update(@RequestBody PayDTO dto) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(dto, pay);
        payService.saveOrUpdate(pay);
        return ResultData.success("success");
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "查询单条支付记录", description = "传入一个id，然后查询一条记录")
    public ResultData<PayDTO> get(@PathVariable("id") Integer id) {
        log.info("start get id: {{}} data...", id);
        Pay pay = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay, payDTO);
        return ResultData.success(payDTO);
    }

    @GetMapping("/pay/getAll")
    @Operation(summary = "查询所有支付记录", description = "查询所有记录")
    public ResultData<List<PayDTO>> getAll() {
        List<Pay> list = payService.list();
        List<PayDTO> res = list.stream().map(pay -> {
            PayDTO payDTO = new PayDTO();
            BeanUtils.copyProperties(pay, payDTO);
            return payDTO;
        }).toList();
        return ResultData.success(res);
    }

    @GetMapping("/pay/circuit/{id}")
    public ResultData<String> circuitTest(@PathVariable("id") Integer id) {
        if (id < 0) throw new ZhanRuntimeException(ZhanExceptionType.IdlowerThanZero);

        if (id == 999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return ResultData.success(String.format("success get id: %s", id));
    }

    @GetMapping("/pay/rateLimit/{id}")
    public ResultData<String> rateLimitTest(@PathVariable("id") Integer id) {
        String res = "success get id: " + id;
        return ResultData.success(res);
    }

}
