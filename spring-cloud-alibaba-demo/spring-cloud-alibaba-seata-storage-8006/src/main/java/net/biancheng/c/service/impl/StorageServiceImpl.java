package net.biancheng.c.service.impl;
import lombok.extern.slf4j.Slf4j;
import net.biancheng.c.entity.Storage;
import net.biancheng.c.mapper.StorageMapper;
import net.biancheng.c.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    @Resource
    StorageMapper storageMapper;

    @Override
    public int decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        log.info("------->storage-service 开始查询商品是否存在");
        // 查询出对应商品的库存信息
        Storage storage = storageMapper.selectByProductId(productId);
        // 剩余数量>=购买数量
        if (storage != null && storage.getResidue().intValue() >= count.intValue()) {
//            Storage storage2 = new Storage();
//            storage2.setProductId(productId);
            storage.setUsed(storage.getUsed() + count);
            storage.setResidue(storage.getTotal().intValue() - storage.getUsed());
            int decrease = storageMapper.decrease(storage);
            log.info("------->storage-service 扣减库存成功");
            return decrease;
        } else {
            log.info("------->storage-service 库存不足，开始回滚！");
            throw new RuntimeException("库存不足，扣减库存失败！");
        }
    }
}