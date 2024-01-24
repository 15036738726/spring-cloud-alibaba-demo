package net.biancheng.c.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private Long id;

    private Long productId;
    // 总数
    private Integer total;
    // 已使用数量
    private Integer used;
    // 剩余库存
    private Integer residue;

}