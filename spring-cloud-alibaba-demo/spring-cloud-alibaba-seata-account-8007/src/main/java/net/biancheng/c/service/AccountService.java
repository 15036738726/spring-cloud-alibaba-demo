package net.biancheng.c.service;

import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;

public interface AccountService {
    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    int decrease(Long userId, BigDecimal money) ;


}