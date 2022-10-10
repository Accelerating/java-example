package com.xxzou.javaexample.redis.springdata;

import com.xxzou.javaexample.redis.springdata.basic.PipelineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zxx
 * @date 2022/10/10 11:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PipelineTest {

    @Autowired
    private PipelineService pipelineService;

    @Test
    public void testPipeline(){
        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("key1", "value2");
                operations.opsForHash().put("hkey1", "field1", "hvalue2");
                operations.opsForList().leftPush("list1", "element1");

                //must return null, otherwise it will throw InvalidDataAccessApiUsageException
                return null;
            }
        };
        pipelineService.testPipeline(sessionCallback);
    }

}
