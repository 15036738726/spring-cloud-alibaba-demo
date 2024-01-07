package net.biancheng.c.service.impl;

import net.biancheng.c.entity.CommonResult;
import net.biancheng.c.entity.Dept;
import net.biancheng.c.service.DeptFeignService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级
 * 解耦回退逻辑
 */
@Component
public class DeptFallbackService implements DeptFeignService {

    @Override
    public CommonResult<Dept> get(int id) {
        CommonResult<Dept> result = new CommonResult(666, "服务降级了。。。,请求方法为get", null);
        return result;
    }

    @Override
    public CommonResult<List<Dept>> list() {
        CommonResult<List<Dept>> result = new CommonResult(666, "服务降级了。。。,请求方法为list", null);
        return result;
    }
}