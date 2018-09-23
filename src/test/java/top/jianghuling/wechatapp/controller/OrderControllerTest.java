package top.jianghuling.wechatapp.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void releaseNewOrder() throws Exception{
        MultiValueMap<String ,String> map = new LinkedMultiValueMap<>();
        map.add("secretId","fd4bd9f9489a089f1620adc44b3e201a");
        map.add("goodsCode","2992");
        map.add("note","wtf");
        map.add("reward","1.3");
        map.add("hostName","wokao");
        map.add("hostPhone","1111111111");
        map.add("takeAddress","南门");
        map.add("destination","是旧社");
        map.add("goodsWeight","很重");
        map.add("starttime","2018-09-22 10:27:00");
        map.add("deadline","2018-09-23 11:11:11");
        map.add("expressType","东郡");


        MvcResult result = mockMvc.perform(post("/order/issue")
                .params(map)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}