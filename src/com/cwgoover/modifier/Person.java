/**
 * Filename:     Person.java
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

public class Person {
    private boolean male = false;

    String parent = "Person's parent";

    protected int age = 30;

    public String name = "Person";

    boolean isMale() {
        return male;
    }
}
