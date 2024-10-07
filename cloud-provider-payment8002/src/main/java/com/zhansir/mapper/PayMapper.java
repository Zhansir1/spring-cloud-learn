package com.zhansir.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhansir.common.entities.Pay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper extends BaseMapper<Pay> {
}