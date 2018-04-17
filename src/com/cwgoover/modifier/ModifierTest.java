/**
 * Filename:     ModifierTest.java
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

public class ModifierTest {

    public static void main(String[] args) {
        Manager manager = new Manager();

        System.out.println("Print isMale=" + manager.isMale());
        System.out.println("Print superIsMale=" + manager.superIsMale());

        System.out.println("Print age=" + manager.getAge());
        System.out.println("Print super age=" + manager.getSuperAge());
    }
}
