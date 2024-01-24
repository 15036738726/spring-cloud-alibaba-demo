package net.biancheng.c.controller;

import lombok.extern.slf4j.Slf4j;
import net.biancheng.c.entity.CommonResult;
import net.biancheng.c.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        int decrease = storageService.decrease(productId, count);
        CommonResult result;
        if (decrease > 0) {
            result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, decrease);
        } else {
            result = new CommonResult(505, "from mysql,serverPort:  " + serverPort, "库存扣减失败");
        }
        return result;
    }
}