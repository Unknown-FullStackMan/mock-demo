package com.mxt.mockdemo;

import com.mxt.mockdemo.entity.Goods;
import com.mxt.mockdemo.service.ExternalService;
import com.mxt.mockdemo.service.GoodsService;
import com.mxt.mockdemo.service.impl.ExternalServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @Author Simple
 * @Create 2022/4/14 16:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockBeanTest {

    /**
     *  @Autowired 配合 @MockBean/@SpyBean使用
     */

    @MockBean
    private GoodsService goodsService;

    @Autowired
    private ExternalServiceImpl externalService;

    @Test
    public void testMockBean() {

        // Given
        Goods goods = new Goods(1,"可乐","可口可乐.png",new Date(),new BigDecimal("52.33"),10086L);
        doReturn(goods).when(goodsService).getGoodsById(1);

        // When
        Goods result = externalService.executeGoodsServiceMethod(1);
        System.out.println(result);

        // When
        Goods result2 = externalService.executeGoodsServiceMethod(2);
        System.out.println(result2);

        // Then
        verify(goodsService, times(1)).getGoodsById(1);

        assertThat("spy类型bean执行真实的方法，得到真实结果，不等于null", result2 == null);
    }

}
