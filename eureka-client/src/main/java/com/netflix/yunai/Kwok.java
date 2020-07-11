package com.netflix.yunai;

/**
 * 学习芋道，来整一个自己的理解进度
 */
public @interface Kwok {

    /**
     * 理解进度
     * @return 最大值为100
     */
    int progress() default 0;
}
