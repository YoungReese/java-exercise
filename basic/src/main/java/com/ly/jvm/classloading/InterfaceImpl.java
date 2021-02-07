package com.ly.jvm.classloading;

/**
 * liyang 2020-12-08
 *
 * 当一个接口在初始化的时候，并不要求父接口全部都完成了初始化，
 * 只有在使用父接口的时候（如引用接口中定义的常量）才会初始化。
 *
 * 接口中无法直接使用static{}语句快
 *
 * static修饰的函数属于类所有，无法被继承，变量可以
 *
 */
public class InterfaceImpl implements SubInterface {
    public static void main(String[] args) {
        InterfaceImpl instance = new InterfaceImpl();
        instance.superDefaultMethod();

        SuperInterface.superStaticMethod(); // 静态方法只能是定义该类的类和实例可以调用
        System.out.println(SubInterface.SUPER_CONSTANT);
    }
}
