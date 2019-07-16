import com.ec.item.ItemApplication;
import com.ec.item.service.BrandService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import com.lh.ec.item.pojo.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ItemApplication.class})
public class service_test {

    @Autowired
    private BrandService brandService;

    @Test
    public void testSaveBrand(){
        Brand brand =  new Brand();
        brand.setName("Test4");
        brand.setLetter('T');

            brandService.save(brand, Arrays.asList(245L));

    }
}
