/**
 * Filename:     Manager.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    17/04/2018
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 17/04/2018        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.modifier;

public class Manager extends Person {

    // FIXME: 如果子类没有定义父类相同的private修饰符修饰的变量，则子类无法直接使用父类该变量，报错！
    private boolean male = true;

    String parent = "Manager's parent";

    // FIXME: 如果子类没有定义父类相同的protected修饰符修饰的变量，可以直接获取age，获取到的是父类的域变量值
//    protected int age = 50;

    public String name = "Manager";

    boolean superIsMale() {
        return super.isMale();
    }

    boolean isMale() {
        return male;
    }

    // FIXME: 如果子类和父类具有同一protected修饰符修饰的变量，不加super，子类使用的是自己的域变量
    int getAge() {
        return age;
    }

    // FIXME: 使用super通知编译器调用父类的域或者方法
    int getSuperAge() {
        return super.age;
    }
}
