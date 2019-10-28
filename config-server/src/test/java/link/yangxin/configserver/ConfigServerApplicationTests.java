package link.yangxin.configserver;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigServerApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1(){
        //stringRedisTemplate.opsForValue().set("name", "杨鑫");
        //stringRedisTemplate.opsForValue().set("age", "25");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));

    }

}
