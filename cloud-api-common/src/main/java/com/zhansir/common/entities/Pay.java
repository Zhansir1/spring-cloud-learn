package com.zhansir.common.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：t_pay
 * 表注释：支付交易表
*/
@TableName(value = "t_pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pay implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 支付流水号
     */
    @TableField(value = "pay_no")
    private String payNo;

    /**
     * 订单流水号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 用户账号ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 交易金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 删除标志，默认0不删除，1删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}