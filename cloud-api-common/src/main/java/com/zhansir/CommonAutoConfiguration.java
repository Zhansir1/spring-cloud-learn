package com.zhansir;

import com.zhansir.common.apis.PayGatewayFeignApi;
import com.zhansir.common.config.RestTemplateConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({RestTemplateConfig.class,
        PayGatewayFeignApi.class})
public class CommonAutoConfiguration {
}
