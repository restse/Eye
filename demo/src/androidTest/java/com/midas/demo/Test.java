package com.midas.demo;

public class Test {


    public static void main(String[] args) {
        //如果start是静态方法  是A  它不是静态方法 运行时和编译时一致 是B
        //静态方法 运行时看父类， 非静态方法 运行和编译时都看 子类
        ((A) new B()).start();//B
        ((A) new B()).startStatic();//A
        A.startStatic();//A
    }

    static class A {
        public void start() {
            System.out.println("A");
        }
        public static void startStatic() {
            System.out.println("A");
        }
    }

    static class B extends A {
        public void start() {
            System.out.println("B");
        }

        public static void startStatic() {
            System.out.println("B");
        }

    }
}
