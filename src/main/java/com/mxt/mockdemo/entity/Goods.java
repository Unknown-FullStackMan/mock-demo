package com.mxt.mockdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Simple
 * @Create 2022/4/14 13:35
 */
@Data
@TableName("goods")
@AllArgsConstructor
@NoArgsConstructor
public class Goods extends Model {

    /**
     * 主键ID
     */
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    private int id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品图标
     */
    private String banner;
    /**
     * 商品有效期
     */
    private Date date;
    /**
     * 商品价格
     */
    @NotNull
    private BigDecimal price;

    /**
     * 商品拥有者
     */
    private Long owner;

}
