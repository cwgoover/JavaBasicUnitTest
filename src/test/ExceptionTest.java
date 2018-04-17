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

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        try {
//            test.testAfterException("aa");
//            test.testCatchExceptionRunStep(5);  // 抛异常
        } catch (IllegalArgumentException e) {
            System.out.println("catch testCatchExceptionRunStep method exception: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("catch testAfterException exception: " + e.getMessage());
        }

        // 尝试Throwable能否捕获Error型错误
        try {
            test.throwOutOfMemoryError();
//        } catch (Exception e) {
//            System.out.println("catch Error with Exception statement");
        } catch (Throwable throwable) {
            // FIXME: Throwable 类型能捕获到Error异常！
            System.out.println("catch throwOutOfMemoryError!~");
            try {
                test.throwStackOverflowError();
            } catch (Throwable e) {
                System.out.println("catch StackOverflowError!~");
            }

        }
    }

    private void testAfterException(String initStr) throws NullPointerException {
        if (TextUtils.isEmpty(initStr)) {
            throw new NullPointerException(this.getClass().getSimpleName() + " initialized failed");
        }
        // FIXME: 抛出异常这里的语句不会再执行
        System.out.println("go on testAfterException method...");
    }

    /**
     * FIXME: 当try语句块抛出异常时，接下来的语句不会再执行，直接跳到catch语句块中执行。虽然catch语句中重新
     * 抛出异常，但是finally语句仍然会执行。其他语句（最后一条不会执行）
     */
    private void testCatchExceptionRunStep(int index) throws IllegalArgumentException {
        try {
            int[] array = new int[]{1,2,3,4};
            int i = array[index];   // 大于等于4抛异常
            System.out.println("get array's data: " + i);    //Unreachable statement if exception occurs
        } catch (Exception e) {
            System.out.println("testCatchExceptionRunStep catch Exception: " + e.getMessage());
            throw new IllegalArgumentException("IllegalArgumentException: " + e.getMessage());
        } finally {
            System.out.println("finally method is running..");
        }
        System.out.println("the last method is running..");
    }

    /**
     * Errors − These are not exceptions at all, but problems that arise beyond the control of the user or the programmer.
     * Errors are typically ignored in your code because you can rarely do anything about an error.
     *
     * For example, if a stack overflow occurs, an error will arise. They are also ignored at the time of compilation.
     */
    private void throwOutOfMemoryError() {
        System.out.println("throw throwOutOfMemoryError");
        throw new OutOfMemoryError("Simlate the throwOutOfMemoryError");
    }

    private void throwStackOverflowError() {
        throw new StackOverflowError("Simulate StackOverflowError");
    }

    private void testGenerics() {
        // TODO:
    }

    // FIXME: <?> 提供了只读的功能，也就是它删减了增加具体类型元素的能力
    public void testWildcars(Collection<?> collection) {
//        collection.add("aa");
//        collection.remove("");
    }

    private void testRegx() {
        String string = "fullimage-200-100";
        Pattern mFullImagePattern = Pattern.compile("fullimage-(\\d+)-(\\d+)");
        Matcher matcher = mFullImagePattern.matcher(string);
        if (matcher.find()) {
            System.out.println("matched!");
            System.out.println("group 1=" + matcher.group(1));
            System.out.println("group 2=" + matcher.group(2));
        } else {
            System.out.println("un matched!");
        }
    }

    class G1<T> {
        T value;

        T getValue() {
            return value;
        }

        // FIXME: 一个类里可以定义两个泛型，但是该泛型只能这个方法中使用
        public <E> E setValue(E v){
            return v;
        }
    }

    interface GI<T> {
        void setValue(T value);
    }

}
