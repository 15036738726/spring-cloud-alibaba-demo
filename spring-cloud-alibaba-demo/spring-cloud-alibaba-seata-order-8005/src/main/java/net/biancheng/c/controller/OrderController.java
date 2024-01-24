package net.biancheng.c.controller;
import io.seata.spring.annotation.GlobalTransactional;
import net.biancheng.c.entity.CommonResult;
import net.biancheng.c.entity.Order;
import net.biancheng.c.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 未进行事务管理的请求方法  异常时,不进行回滚
     * @param productId
     * @param count
     * @param money
     * @return
     */
    @GetMapping("/order/create/{productId}/{count}/{money}")
    public CommonResult create(@PathVariable("productId") Integer productId, @PathVariable("count") Integer count
            , @PathVariable("money") BigDecimal money) {
        Order order = new Order();
        order.setProductId(Integer.valueOf(productId).longValue());
        order.setCount(count);
        order.setMoney(money);
        return orderService.create(order);
    }

    /**
     * 通过注解进行事务管理
     * @param productId
     * @param count
     * @param money
     * @return
     */
    @GetMapping("/order/createByAnnotation/{productId}/{count}/{money}")
    @GlobalTransactional(name = "c-biancheng-net-create-order", rollbackFor = Exception.class)
    public CommonResult createByAnnotation(@PathVariable("productId") Integer productId, @PathVariable("count") Integer count
            , @PathVariable("money") BigDecimal money) {
        Order order = new Order();
        order.setProductId(Integer.valueOf(productId).longValue());
        order.setCount(count);
        order.setMoney(money);
        return orderService.create(order);
    }
}