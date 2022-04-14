package com.mxt.mockdemo.pojo;

/**
 * @Author Simple
 * @Create 2022/4/13 11:22
 */
public class AdditionCombination {

    private AdditionV1 additionV1;
    private AdditionV2 additionV2;

    public int sum (int a, int b) {
        return additionV1.add(a, b);
    }
    public int sum2 (int a, int b) {
        return additionV2.add(a, b);
    }
}