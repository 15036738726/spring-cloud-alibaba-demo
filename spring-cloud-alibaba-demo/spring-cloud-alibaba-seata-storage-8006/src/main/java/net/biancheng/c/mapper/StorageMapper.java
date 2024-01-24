package net.biancheng.c.mapper;
import net.biancheng.c.entity.Storage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper {
    Storage selectByProductId(Long productId);

    int decrease(Storage record);
}