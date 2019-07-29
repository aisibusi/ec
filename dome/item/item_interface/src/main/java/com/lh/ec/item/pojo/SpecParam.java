package com.lh.ec.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Table(name = "tb_spec_param")
public class SpecParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    @Column(name = "`numeric`")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
}
