package net.biancheng.c.controller;

import lombok.extern.slf4j.Slf4j;
import net.biancheng.c.entity.CommonResult;
import net.biancheng.c.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {
    @Resource
    private AccountService accountService;

    @Value("${server.port}")
    private String serverPort;
    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        int decrease = accountService.decrease(userId, money);
        CommonResult result;
        if (decrease > 0) {
            result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, decrease);
        } else {
            result = new CommonResult(505, "from mysql,serverPort:  " + serverPort, "账户扣减失败");
        }
        return result;
    }
}