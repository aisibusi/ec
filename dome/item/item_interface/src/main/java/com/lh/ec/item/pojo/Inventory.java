package com.lh.ec.item.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_inventory")
@Data
public class Inventory {

    @Id
    private Long skuId;

    private Integer seckillInventory;// 秒杀可用库存

    private Integer seckillTotal;// 已秒杀数量

    private Integer inventory;// 正常库存
}