package net.biancheng.c.mapper;


import net.biancheng.c.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {
    Account selectByUserId(Long userId);

    int decrease(Long userId, BigDecimal money);
}