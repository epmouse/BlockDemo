package org.cityu.cs.ian.test;


import org.cityu.cs.ian.service.Threads.impl.TaskService;
import org.cityu.cs.ian.util.SHA256;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-core-test.xml"})
public class MyTest {
    @Autowired
    TaskService taskService;

    @Before
    public void init() {
        //在运行测试之前的业务代码
    }

    @After
    public void after() {
        //在测试完成之后的业务代码
    }

        String hash = "aaaaaaaaaaaaaaa";
    @Test
    public void test1() {
        taskService.powCalculate();
    }
    @Test
    public void test2() {
     assert  "000d9fd67c7f357f8c49abf2da30b1d059a66e631ffa4548bee34ef873b35dd4".equals(SHA256.getSHA256StrJava(TaskService.BASENACL+"1508322382103"+1816));
    }
}