/**
 * Filename:     ExceptionTest.java
 * Description:
 * Author:       CAO Ting
 * Version:      1.0
 * Create at:    14/11/2017
 * Modification History:
 * Date             Author        Version     Description
 * ------------------------------------------------------------------
 * 14/11/2017        caozangzang     1.0       1.0 Version
 */
package test;

import com.cwgoover.danmu.android.TextUtils;

import java.io.FileNotFoundException;
import java.util.Collection;

public class ExceptionTest {
    public static void main(String[] args) {
//        String string = "fullimage-200-100";
//        Pattern mFullImagePattern = Pattern.compile("fullimage-(\\d+)-(\\d+)");
//        Matcher matcher = mFullImagePattern.matcher(string);
//        if (matcher.find()) {
//            System.out.println("matched!");
//            System.out.println("group 1=" + matcher.group(1));
//            System.out.println("group 2=" + matcher.group(2));
//        } else {
//            System.out.println("un matched!");
//        }

        ExceptionTest test = new ExceptionTest();
//        try {
//            test.throwException1();
//        } catch (Exception e) {
////            e.printStackTrace();
//            System.out.println("main catch: " + e.getMessage());
//        }

        // 测试抛出异常后是否还能执行下面的语句
        try {
            test.init("aa");
        } catch (IllegalArgumentException e) {
            System.out.println("catch init exception");
        }
    }

    private void throwError() {
        throw new FileNotFoundException("");
    }

    private void init(String initStr) throws IllegalArgumentException {
        if (TextUtils.isEmpty(initStr)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " initialized failed");
        }
        System.out.println("go on init method...");
    }

    private void throwException1() throws NullPointerException {
        try {
            throw new NullPointerException("NullPointerException");
        } catch (Exception e) {
            System.out.println("throwException1 catch: " + e.getMessage());
            throw new NullPointerException("NullPointerException2");
        }
    }

    private void testGenerics() {

    }

    // <?> 提供了只读的功能，也就是它删减了增加具体类型元素的能力
    public void testWildcars(Collection<?> collection) {
//        collection.add("aa");
//        collection.remove("");
    }

    class G1<T> {
        T value;

        T getValue() {
            return value;
        }

        public <E> E setValue(E v){
            return v;
        }
    }

    interface GI<T> {
        void setValue(T value);
    }

}
