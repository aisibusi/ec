package com.lh.ec.common.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lh
 * @date 2019/6/17
 */
@Data
@NoArgsConstructor
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }


}
