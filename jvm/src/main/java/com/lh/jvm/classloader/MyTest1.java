package com.lh.jvm.classloader;

public class MyTest1 {
    public static void main(String[] args) {
        System.out.println(MyChild.str);
    }
}
class MyParent{
    public static String str = "hi";
    static{
        System.out.println("heihei");
    }
}

class MyChild extends MyParent{
    static{
        System.out.println("I'm the child");
    }
}