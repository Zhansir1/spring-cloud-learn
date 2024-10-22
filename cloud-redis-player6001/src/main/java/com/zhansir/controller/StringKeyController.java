package com.zhansir.controller;

import com.zhansir.common.resp.ResultData;
import com.zhansir.pojo.dto.User;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringKeyController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redis/string")
    public ResultData<String> redis(@RequestParam("key") String key)
    {
        String res = (String)redisTemplate.opsForValue().get(key);
        return ResultData.success(res);
    }

    @PostMapping("/redis/string")
    public ResultData<String> redisPost(@RequestParam("key") String key,
                                        @RequestParam("value") Object value)
    {
        redisTemplate.opsForValue().set(key, value);
        return ResultData.success("success");
    }

    @GetMapping("redis/userTest")
    public ResultData<String> userTest()
    {
        User user = new User();
        user.setId(1);
        user.setName("zhansir");
        redisTemplate.opsForValue().set("zhansir", user);
        User zhansir = (User) redisTemplate.opsForValue().get("zhansir");
        System.out.println(zhansir);
        return ResultData.success("success");
    }

}
