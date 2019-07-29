import com.ec.search.EcSearchApplication;
import com.ec.search.client.GoodsClient;
import com.ec.search.pojo.Goods;
import com.ec.search.repository.GoodsRepository;
import com.ec.search.service.SearchService;
import com.lh.ec.common.vo.PageResult;
import com.lh.ec.item.pojo.Spu;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcSearchApplication.class)
public class ElasticsearchTest {

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;
    @Test
    public void creatIndex(){
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

    @Test
    public void loadData() {
        int page = 1;
        int size = 0;
        int rows = 100;
        do {
            PageResult<Spu> result = goodsClient.querySpuByPage(page, rows, null, true);
            ArrayList<Goods> goodList = new ArrayList<>();
            List<Spu> spus = result.getItems();
            size = spus.size();
            for (Spu spu : spus) {
                try {
                    Goods g = searchService.buildGoods(spu);
                    goodList.add(g);

                } catch (Exception e) {
                    break;
                }
            }
            this.repository.saveAll(goodList);
            page++;
        } while (size == 100);
    }
}
