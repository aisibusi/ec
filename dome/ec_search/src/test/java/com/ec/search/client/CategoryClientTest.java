package com.ec.search.client;

import com.ec.search.EcSearchApplication;
import com.lh.ec.item.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcSearchApplication.class)
public class CategoryClientTest {


    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategories(){
        List<Category> categories =
                categoryClient.queryCategoryByIds(Arrays.asList(1L, 2L, 3L));
        categories.forEach(System.out::println);

    }
}