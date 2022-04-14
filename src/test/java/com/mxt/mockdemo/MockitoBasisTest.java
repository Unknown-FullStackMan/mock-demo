package com.mxt.mockdemo;


import com.mxt.mockdemo.pojo.AdditionV1;
import com.mxt.mockdemo.pojo.AdditionV2;
import com.mxt.mockdemo.pojo.AdditionCombination;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @Author Simple
 * @Create 2022/4/12 11:21
 */

public class MockitoBasisTest {

    /**
     * @InjectMocks与 @Mock注解生效的三种方式
     *
     * 方式一：使用@RunWith(MockitoJUnitRunner.class)
     *     @RunWith(MockitoJUnitRunner.class)
     * 方式二:
     *     @Rule
     *     public MockitoRule mock = MockitoJUnit.rule();
     * 方式三：
     *     @Before
     *     public void before(){
     *        MockitoAnnotations.initMocks(this);
     *     }
     */

    @Rule
    public MockitoRule mock = MockitoJUnit.rule();

    /**
     *  静态方法 mock() 模拟出一个实例
     */
    @Test
    public void testVerify() {
        // Mock creation
        List mockedList = mock(List.class);

        // 调用add("one")行为
        mockedList.add("one");
        // 调用add("one")行为
        mockedList.clear();

        // 检验add("one")是否已被调用
        verify(mockedList).add("one");
        // 检验clear()是否已被调用
        verify(mockedList).clear();
        // 检验add("111")是否已被调用
        verify(mockedList).add("111");
    }

    /**
     *  静态方法 mock() 模拟出一个实例，
     */
    @Test
    public void testAction() {
        List<String> list =  mock(List.class);
        // 执行list.get(0)时会返回一个stub值(Mock的假数据)
        when(list.get(0)).thenReturn("first");
        System.out.println(list.get(0));
        // 返回null,因为没有stub 999的数值
        System.out.println(list.get(999));


        when(list.get(1)).thenThrow(new RuntimeException());
        System.out.println(list.get(1));

        list.add("second");
        list.add("third");
        // return null
        System.out.println(list.get(2));
    }

    /**
     *  静态方法 mock() 模拟出一个实例，
     */
    @Test
    public void testMatcher() {
        List<String> list =  mock(List.class);
        // 执行list.get(0)时会返回一个stub值(Mock的假数据)
        when(list.get(0)).thenReturn("first");
        System.out.println(list.get(0));


        // Stubbing using built-in anyInt() argument matcher
        when(list.get(anyInt())).thenReturn("anyInt() argument matcher");
        System.out.println(list.get(1));

//        when(list.contains(argThat())).thenReturn("element");

        verify(list).get(1);
        list.add("xa");
//        verify(list).add(argThat(e->e.length()>5));

    }

    /**
     * 校验次数
     */
    @Test
    public void testValidateTimes() {
        List<String> list =  mock(List.class);
        list.add("once");
        list.add("twice");
        list.add("twice");
        list.add("three times");
        list.add("three times");
        list.add("three times");

        verify(list).add("once");
        // 验证调用做相同操作的次数
        verify(list,times(1)).add("once");
        verify(list,times(2)).add("twice");
        verify(list,times(3)).add("three times");

        // Verification using never(). never() is an alias to times(0)
        verify(list,never()).add("never happened");

        // Verification using atLeast()/atMost()
        verify(list,atLeastOnce()).add("once");
        verify(list,atLeast(2)).add("twice");
        verify(list,atMost(5)).add("three times");
    }

    /**
     * 抛出异常
     */
    @Test
    public void testExceptions() {
        List<String> list =  mock(List.class);
        doThrow(new RuntimeException()).when(list).clear();
        // 调用clear()就报错
        list.clear();
    }

    @Test
    public void testInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List<String> singleMock = mock(List.class);
        // Use a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");
        System.out.println(singleMock);
        // Create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);
        // Following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        // Use mocks
        firstMock.add("was called first");
        secondMock.add("was called second");
        // Create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder02 = inOrder(firstMock, secondMock);
        // Following will make sure that firstMock was called before secondMock
        inOrder02.verify(firstMock).add("was called first");
        inOrder02.verify(secondMock).add("was called second");
    }


    /**
     * 对于同一个方法，如果我们想让其在 多次调用 中分别 返回不同 的数值，那么就可以使用存根连续调用
     */
    @Test
    public void testStub() {
        List<String> singleMock = mock(List.class);


        /*
        * 写法一
         */
        when(singleMock.get(1)).thenReturn("once")
                .thenReturn("twice")
                .thenReturn("three times");

        /*
         * 写法二
         */
        when(singleMock.get(1)).thenReturn("once","twice","three times");

        // 每一次调用，返回的内容都不一样。
        System.out.println(singleMock.get(1));
        System.out.println(singleMock.get(1));
        System.out.println(singleMock.get(1));
        // 第4次调用 就返回最后一次设置的thenReturn()值
        System.out.println(singleMock.get(1));
    }

    /**
     * 监视真实对象
     * 前面使用的都是 mock 出来一个对象。这样，当 没有配置/存根 其具体行为的话，结果就会返回 空类型。而如果使用 特务对象（spy），
     * 那么对于 没有存根 的行为，它会调用 原来对象 的方法。可以把 spy 想象成局部 mock。
     *
     * 注意：由于 spy 是局部 mock，所以有时候使用 when(Object) 时，无法做到存根作用。此时，就可以考虑使用
     * doReturn() | Answer() | Throw() 这类方法进行存根.
     *
     * spy 并不是 真实对象 的 代理。
     * 相反的，它对传递过来的 真实对象 进行 克隆。所以，对 真实对象 的任何操作，spy 对象并不会感知到。
     * 同理，对 spy 对象的任何操作，也不会影响到 真实对象。
     */
    @Test
    public void testSpy() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");

        // 能获取到真实数据
        System.out.println(spy.get(0));
        System.out.println(spy.get(1));
        verify(spy).add("one");
        verify(spy).add("two");


        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
        System.out.println(spy.get(0));
        assertThat(spy.get(1), true);
        assertThat(spy.get(1), false);
    }






    //foo 对象内部的成员变量会自动被 @Mock 注解的生成的对象注入
    @InjectMocks
    private AdditionCombination additionCombination;
    //bar 对象会自动的注入到 @InjectMocks 注解的对象的成员变量中去。
    @Mock
    private AdditionV1 additionV1;
    @Spy
    private AdditionV2 additionV2;

    /**
     * 测试@InjectMocks @Mock
     */
    @Test
    public void testMockAndInjectMock() {
        // 设置存根
        when(additionV1.add(1,2)).thenReturn(4);

        int result = additionCombination.sum(1,2);
        int result2 = additionCombination.sum(1,2);

        //验证
        Assert.assertEquals(4,result);
        // 报错
        Assert.assertEquals(3,result2);
    }

    /**
     * 测试@Spy
     */
    @Test
    public void testAtSpy() {

        int result2 = additionCombination.sum2(1,2);
        // 不报错，即使不设置存根都会调用真实的方法，使用的@Spy
        Assert.assertEquals(3,result2);

        int result = additionCombination.sum(1,2);
        // 报错,因为没有设置存根，返回null，使用的@mock
        Assert.assertEquals(3,result);

    }

    /**
     * spy特务对象使用doReturn
     */
    @Test
    public void testDoReturn() {

        List list = new LinkedList();
        List spy = spy(list);

        //以下代码是不可能: spy.get(0)方法实际的代码会被调用， 会抛出
        //IndexOutOfBoundsException (list仍然是空的)。
        //为什么在存根的时候就会调用方法实际的代码？这样不就无法对此方法进行存根了吗？
        //因为在执行when(spy.get(0))的时候首先执行的是when()方法内的spy.get(0)；
        //而此时spy.get(0)还没有进行存根。故此方法的实际代码会被调用。要解决这个问题，
        //请使用doXXX()方法配合when()进行存根。
        when(spy.get(0)).thenReturn("foo");

        //你需要用 doReturn() 去存根
        doReturn("foo").when(spy).get(0);
    }



    /**
     * Answer测试
     */
    @Test
    public void testAnswer() {
        Answer answer = new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                if ("add" == invocationOnMock.getMethod().getName()) {
                    return 1;
                }else if ("get" .equals(invocationOnMock.getMethod().getName())) {
                    return "get list content";
                }
                return null;
            }
        };
        AdditionV1 additionV1 = mock(AdditionV1.class);
        when(additionV1.add(1,2)).thenAnswer(answer);
        System.out.println(additionV1.add(1,2));

        List list = mock(List.class);
        when(list.get(0)).thenAnswer(answer);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));

    }

    /**
     * 测试断言
     * 我们需要在assert关键字后放置一个布尔值（也可以是一个表达式，这个表达式也会变成一个布尔值），
     * 当这个布尔值为 true 时，会通过整个断言；当这个布尔值为 false 时，这个断言就会抛出一个错误，这会让整个程序停止。
     */
    @Test
    public void testAssert() {
        assert false;
    }

    @Test
    public void testAssertV2() {
        assert true : "true,不会打印出断言信息";
        assert false : "false,打印出断言的信息";
    }

    @Test
    public void testAssertV3() {
        AdditionV1 additionV1 = mock(AdditionV1.class);
        when(additionV1.add(1,2)).thenReturn(7);
        assert  additionV1.add(1,2) == 7 : "true,不会打印出断言信息";
        assert  additionV1.add(1,2) == 3 : "false,不会打印出断言信息";
    }


}

