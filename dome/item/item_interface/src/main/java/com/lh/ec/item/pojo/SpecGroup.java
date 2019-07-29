package com.lh.ec.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Table(name = "tb_spec_group")
public class SpecGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cid;
    @Column
    private String name;

    @Transient
    private List<SpecParam> params;
}
