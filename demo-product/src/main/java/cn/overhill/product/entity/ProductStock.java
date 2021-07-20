package cn.overhill.product.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "product_stock")
@Data
public class ProductStock {

    private Long id;

    private String productName;


    private Integer productId;

    private Integer quantity;
}
