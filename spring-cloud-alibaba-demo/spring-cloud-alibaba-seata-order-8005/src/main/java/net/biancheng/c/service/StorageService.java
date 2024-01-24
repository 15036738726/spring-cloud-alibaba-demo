package net.biancheng.c.service;

import net.biancheng.c.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 接口绑定,绑定库存服务接口
 */
@FeignClient(value = "spring-cloud-alibaba-seata-storage-8006")
public interface StorageService {
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}