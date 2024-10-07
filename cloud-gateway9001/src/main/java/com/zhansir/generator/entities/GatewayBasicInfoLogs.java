package com.zhansir.generator.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("gateway_basic_info_logs")
public class GatewayBasicInfoLogs {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String requestId;
    private String requestPath;
    private String remoteAddress;
    private String requestMethod;
    private String requestHost;
    private String rawQuery;
    private String responseStatus;
    private Long timeTakenMs;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdTime;
}
