package com.midas.demo.zzzz;

/**
 * Created by midas on 2019/8/6
 * desc: java 单例
 */

public class SingletonDemoJava {

    private SingletonDemoJava() {
    }

    /**
     * 静态内部类
     */
    private static class SingleHolder {
        private static SingletonDemoJava instance = new SingletonDemoJava();
    }

    public static SingletonDemoJava getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 饿汉式
     */
    private static SingletonDemoJava instance1 = new SingletonDemoJava();

    public static SingletonDemoJava getInstance1() {
        return instance1;
    }

    /**
     * 双重校验锁式
     */
    private volatile static SingletonDemoJava instance2;

    public static SingletonDemoJava getInstance2() {
        if (null == instance2) {
            synchronized (SingletonDemoJava.class) {
                if (null == instance2) {
                    instance2 = new SingletonDemoJava();
                }
            }
        }
        return instance2;
    }

    /**
     * 懒汉式
     */
    private static SingletonDemoJava instance3;

    public static SingletonDemoJava getInstance3() {
        if (instance3 == null) {
            instance3 = new SingletonDemoJava();
        }
        return instance3;
    }

    /**
     * 线程安全的懒汉式
     */
    private static SingletonDemoJava instance4;

    public static synchronized SingletonDemoJava getInstance4() {//使用同步锁
        if (instance4 == null) {
            instance4 = new SingletonDemoJava();
        }
        return instance4;
    }

}
