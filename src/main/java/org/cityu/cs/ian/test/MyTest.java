package org.cityu.cs.ian.test;



import org.cityu.cs.ian.service.Threads.impl.TaskService;
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
    @Test
    public void test1(){
        int totalBlockCount = taskService.getTotalBlockCount();
        System.out.println(totalBlockCount);
    }
}