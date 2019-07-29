package com.lh.ec.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Table(name = "tb_spu")
public class Spu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subTitle;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Long brandId;
    private Boolean saleable;
    private Boolean valid;
    private Date createTime;

    @JsonIgnore
    private Date lastUpdateTime;


    //spu所属的分类名称
    @Transient
    private String cname;

    //spu所属品牌名
    @Transient
    private String bname;

    //spu详情
    @Transient
    private SpuDetail spuDetail;

    //sku集合
    @Transient
    private List<Sku> skus;
}
