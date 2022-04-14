package com.mxt.mockdemo;

import com.mxt.mockdemo.entity.Goods;
import com.mxt.mockdemo.service.GoodsService;
import com.mxt.mockdemo.service.impl.ExternalServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @Author Simple
 * @Create 2022/4/14 15:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MockAndInjectMocksTest {


    /**
     * 1. @InjectMocks 只能配合 @Mock/@Spy 使用，不能使用@SpyBean,会报NPE。
     * 2. @InjectMocks 不能注解到接口上
     * 3.使用@InjectMocks,即使用了@Spy，如果其更下一层没有mock（dao层），其也会返回null。
     * 4. 如果把  @InjectMocks 化成 @Autowired,那么程序将不走mock的流程，不会用到mock的数据。
     */

    @Mock
    private GoodsService goodsService;

    @InjectMocks
    private ExternalServiceImpl externalService;

    @Test
    public void testSpy() {

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
