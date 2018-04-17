/**
 * Filename:     JavaBasic.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    22/09/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 22/09/2017        caozangzang     1.0       1.0 Version
 */
package com.cwgoover.danmu.android;

public class JavaBasic {

    // 数据类型可以自动的从低精度->高精度
    // 如果想高精度自动转为低精度，比如float -> int 编译会报错，因为损失精度
    // 这种情况可以用强转(高精度->低精度)
//        int i = 1.5;
    int i = (int) 1.5;
    // 在java中，小数默认是double类型，如果直接赋给float，因为损失精度会编译失败
//        float f = 3.4;
    // 如果需要将小数直接赋给float，可以在数字之后加一个"f"
    float f = 3.4f;

    private void remember() {

    }
}
