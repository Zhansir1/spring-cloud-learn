package com.zhansir.gateway;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.zhansir.generator.service.GatewayBasicInfoLogsService;
import com.zhansir.generator.entities.GatewayBasicInfoLogs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TimeConsumeGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private GatewayBasicInfoLogsService gatewayBasicInfoLogsService;

    public final static String StartTimeTag = "startTime";

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        DateTime startTime = DateUtil.date();
        exchange.getAttributes().put(StartTimeTag, startTime);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            DateTime dateTime = (DateTime) exchange.getAttributes().get(StartTimeTag);
            if (dateTime != null) {
                DateTime endTime = DateUtil.date();
                long betweenMs = DateUtil.betweenMs(dateTime, endTime);
                GatewayBasicInfoLogs logs = GatewayBasicInfoLogs.builder()
                        .requestId(exchange.getRequest().getId())
                        .requestPath(exchange.getRequest().getPath().toString())
                        .remoteAddress(exchange.getRequest().getRemoteAddress().toString())
                        .requestMethod(exchange.getRequest().getMethod().toString())
                        .requestHost(exchange.getRequest().getURI().getHost())
                        .rawQuery(exchange.getRequest().getURI().getRawQuery())
                        .responseStatus(exchange.getResponse().getStatusCode().toString())
                        .timeTakenMs(betweenMs)
                        .startTime(dateTime.toLocalDateTime())
                        .endTime(endTime.toLocalDateTime())
                        .createdTime(DateUtil.date().toLocalDateTime())
                        .build();
                gatewayBasicInfoLogsService.save(logs);
//                log.info("Request id is :" + exchange.getRequest().getId());
//                log.info("Request Path is :" + exchange.getRequest().getPath());
//                log.info("Request RemoteAddress is :" + exchange.getRequest().getRemoteAddress());
//                log.info("Request Method is :" + exchange.getRequest().getMethod());
//                log.info("Request Host is :" + exchange.getRequest().getURI().getHost());
//                log.info("Request RawQuery is :" + exchange.getRequest().getURI().getRawQuery());
//                log.info("Response Status Code is :" + exchange.getResponse().getStatusCode());
//                long betweenMs = DateUtil.betweenMs(dateTime, endTime);
//                log.info("Request between ms is :" + betweenMs);
//                log.info("==========================================================================");
            }
        }));
    }
}
