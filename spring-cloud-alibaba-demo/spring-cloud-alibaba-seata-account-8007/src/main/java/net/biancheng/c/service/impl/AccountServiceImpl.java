package net.biancheng.c.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.biancheng.c.entity.Account;
import net.biancheng.c.mapper.AccountMapper;
import net.biancheng.c.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Override
    public int decrease(Long userId, BigDecimal money) {

        log.info("------->account-service 开始查询账户余额");
        Account account = accountMapper.selectByUserId(userId);
        log.info("------->account-service 账户余额查询完成，" + account);
        // 账户余额 >= 使用的金额
        if (account != null && account.getResidue().intValue() >= money.intValue()) {
            log.info("------->account-service 开始从账户余额中扣钱！");
            int decrease = accountMapper.decrease(userId, money);
            log.info("------->account-service 从账户余额中扣钱完成");
            return decrease;
        } else {
            log.info("账户余额不足，开始回滚！");
            throw new RuntimeException("账户余额不足！");
        }
    }

}