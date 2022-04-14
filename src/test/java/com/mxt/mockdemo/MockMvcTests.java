package com.mxt.mockdemo;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class MockMvcTests {


    /**
     * 开启@AutoConfigureMockMvc注解，注入MockMvc对象，
     * 我们可以通过此对象来模拟HttpServletRequest对象
     */
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testTestControllerMethodTestMockMvc() throws Exception {

        MvcResult mvcResult = mockMvc
                // 模拟发起http请求，调用Controller层接口“test/mockmvc”
                .perform(get("/test/mockmvc?name=Simple"))
                // 期望值，请求返回的信息
                .andExpect(status().isOk())
                // 期望返回内容
//                .andExpect(content().string("a"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

}
