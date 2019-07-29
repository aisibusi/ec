package com.ec.item.service.impl;

import com.ec.item.repository.InventoryRepository;
import com.ec.item.repository.SkuRepository;
import com.ec.item.repository.SpuDetailRepository;
import com.ec.item.repository.SpuRepository;
import com.ec.item.service.GoodsService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Inventory;
import com.lh.ec.item.pojo.Sku;
import com.lh.ec.item.pojo.Spu;
import com.lh.ec.item.pojo.SpuDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private SpuRepository spuRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SpuDetailRepository spuDetailRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable) {

        Page<Spu> spuPage = null;
        try {
            spuPage = spuRepository.findAll((root, criteriaQuery, builder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(key))
                    predicates.add(builder.like(root.get("title"), "%" + key + "%"));
                if(saleable != null)
                    predicates.add(builder.equal(root.get("saleable"), saleable));
                return builder.and(predicates
                        .toArray(new Predicate[predicates.size()]));
            }, PageRequest.of(page - 1, rows));
        } catch (Exception e) {
            log.info("Unexpected exception in [querySpuByPage]. The info is " + e.getMessage());
        }
        return new PageResult<>(spuPage.getTotalElements(), spuPage.getTotalPages()
                , spuPage.getContent());
    }

    @Override
    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        SpuDetail spuDetail = spuDetailRepository.getOne(spuId);
        if (spuDetail == null) {
            throw new EcException(ExceptionEnum.SPU_NOT_FOUND);
        }
        return spuDetail;
    }

    @Override
    public List<Sku> querySkuBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuRepository.findBySpuIdIs(spuId);
        if (CollectionUtils.isEmpty(skuList)) {
            throw new EcException(ExceptionEnum.SKU_NOT_FOUND);
        }

        //查询库存
        for (Sku sku1 : skuList) {
            sku1.setInventory(inventoryRepository.getOne(sku1.getId()).getInventory());
        }
        return skuList;
    }

    @Override
    public List<Sku> querySkuByIds(List<Long> ids) {
        List<Sku> skus = skuRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(skus)) {
            throw new EcException(ExceptionEnum.GOODS_NOT_FOUND);
        }
        //填充库存
        fillStock(ids, skus);
        return skus;
    }


    @Override
    public Spu querySpuBySpuId(Long spuId) {
        //根据spuId查询spu
        Spu spu = spuRepository.getOne(spuId);

        //查询spuDetail
        SpuDetail detail = querySpuDetailBySpuId(spuId);

        //查询skus
        List<Sku> skus = querySkuBySpuId(spuId);

        spu.setSpuDetail(detail);
        spu.setSkus(skus);

        return spu;

    }
//    @Transactional
//    @Override
//    public void decreaseStock(List<CartDto> cartDtos) {
//        for (CartDto cartDto : cartDtos) {
//            int count = stockMapper.decreaseStock(cartDto.getSkuId(), cartDto.getNum());
//            if (count != 1) {
//                throw new EcException(ExceptionEnum.STOCK_NOT_ENOUGH);
//            }
//        }
//    }

    private void fillStock(List<Long> ids, List<Sku> skus) {
        //批量查询库存
        List<Inventory> stocks = inventoryRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(stocks)) {
            throw new EcException(ExceptionEnum.INVENTORY_NOT_FOUND);
        }
        //首先将库存转换为map，key为sku的ID
        Map<Long, Integer> map = stocks.stream().collect(Collectors.toMap(s -> s.getSkuId(), s -> s.getInventory()));

        //遍历skus，并填充库存
        for (Sku sku : skus) {
            sku.setInventory(map.get(sku.getId()));
        }
    }
}
