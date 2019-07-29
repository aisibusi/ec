package com.lh.ec.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Table(name = "tb_inventory")
public class Inventory implements Serializable {

    @Id
    private Long skuId;

    private Integer seckillInventory;// 秒杀可用库存

    private Integer seckillTotal;// 已秒杀数量

    private Integer inventory;// 正常库存
}