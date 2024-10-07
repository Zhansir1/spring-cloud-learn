package com.zhansir.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhansir.common.entities.Pay;
import com.zhansir.mapper.PayMapper;
import com.zhansir.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService{
}
