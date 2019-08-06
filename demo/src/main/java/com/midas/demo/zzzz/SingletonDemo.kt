package com.midas.demo.zzzz

/**
 * Created by midas on 2019/8/6

 * desc: kotlin 静态内部类单例
 */
class SingletonDemo private constructor() {

    /**静态内部类*/
    companion object {
        val instance = SingleHolder.holder
    }

    private object SingleHolder {
        val holder =  SingletonDemo()
    }


}

/**
 * 饿汉式
 */
object SingletonDemo2

/**
 * 双重校验锁式
 */
class  SingletonDemo3 private constructor() {
    companion object {
        val instance: SingletonDemo3 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemo3()
        }
    }
}

/**懒汉式*/
class SingletonDemo4 private constructor() {
    companion object {
        private var instance: SingletonDemo4? = null
            get() {
                if (field == null) {
                    field = SingletonDemo4()
                }
                return field
            }

        fun get(): SingletonDemo4 {
            //细心的小伙伴肯定发现了，这里不用getInstance作为为方法名，是因为在伴生对象声明时，内部已有getInstance方法，所以只能取其他名字
            return instance!!
        }
    }
}

/**线程安全的懒汉式*/
class SingletonDemo5 private constructor() {
    companion object {
        private var instance: SingletonDemo5? = null
            get() {
                if (field == null) {
                    field = SingletonDemo5()
                }
                return field
            }

        @Synchronized
        fun get(): SingletonDemo5 {
            return instance!!
        }
    }
}