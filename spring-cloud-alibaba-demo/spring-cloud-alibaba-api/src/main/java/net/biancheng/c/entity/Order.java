package net.biancheng.c.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    // 用户ID
    private Long userId;
    // 产品ID
    private Long productId;
    // 购买数量
    private Integer count;
    // 花费金额
    private BigDecimal money;
    // 订单状态
    private Integer status;
}