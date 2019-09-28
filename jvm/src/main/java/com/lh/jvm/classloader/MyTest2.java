package com.lh.jvm.classloader;
// 常量在编一阶段会存入到调用这个常量的方法所在的类的常量池中，
//调用类并没有直接引用到定义变量的类，因此不会触发定义常量的类的初始化
public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.str);
    }
}

class MyParent2{
    public static final String str = "hello";
    static{
        System.out.println("myp2 static block");
    }
}
