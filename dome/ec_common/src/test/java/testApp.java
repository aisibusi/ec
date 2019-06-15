
import com.fasterxml.jackson.core.type.TypeReference;
import com.lh.ec.common.utils.JsonUtils;
import org.junit.Test;


import java.util.List;
import java.util.Map;


public class testApp {


    class User{
        String name;
        int age;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    public void testJsonUtils(){
        User user = new User();
        user.setName("a");
        user.setAge(12);
        
        String json = "[{\"name\": \"Jack\", \"age\": 12}, {\"name\": \"Rose\", \"age\": 48}]";
        System.out.println(json);
        List<Map<String, String>> maps = JsonUtils.nativeRead(json, new TypeReference<List<Map<String, String>>>() {
        });

        maps.stream().forEach(System.out::println);
    }
}
