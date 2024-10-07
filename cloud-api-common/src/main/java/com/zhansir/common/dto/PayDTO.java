package com.zhansir.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDTO implements Serializable {
    @Schema(title = "主键id")
    private Integer id;
    @Schema(title = "支付id")
    private String payNo;
    @Schema(title = "订单id")
    private String orderNo;
    @Schema(title = "用户id")
    private Integer userId;
    @Schema(title = "支付金额")
    private BigDecimal amount;
    @Schema(title = "支付金额")
    private Integer deleted;
}
