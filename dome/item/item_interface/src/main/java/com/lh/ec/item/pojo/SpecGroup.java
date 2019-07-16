package com.lh.ec.item.pojo;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tb_spec_group")
public class SpecGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;
    @Column
    private String name;

    @Transient
    private List<SpecParam> params;
}
