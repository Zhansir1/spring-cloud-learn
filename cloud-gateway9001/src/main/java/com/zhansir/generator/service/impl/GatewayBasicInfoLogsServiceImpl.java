package com.zhansir.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhansir.generator.entities.GatewayBasicInfoLogs;
import com.zhansir.generator.service.GatewayBasicInfoLogsService;
import com.zhansir.generator.mapper.GatewayBasicInfoLogsMapper;
import org.springframework.stereotype.Service;

/**
* @author MI
* @description 针对表【gateway_basic_info_logs】的数据库操作Service实现
* @createDate 2024-09-29 14:57:04
*/
@Service
public class GatewayBasicInfoLogsServiceImpl
        extends ServiceImpl<GatewayBasicInfoLogsMapper, GatewayBasicInfoLogs>
        implements GatewayBasicInfoLogsService
{
}




