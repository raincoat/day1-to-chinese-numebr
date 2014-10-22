package com.mobius.ToChineseNumber;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void numberAssertEquals(String expected, String number)
    {
        assertEquals(expected, App.convertToChinese(number));
    }

    public void testConvertArabicnumbers()
    {
        // See more http://en.wikipedia.org/wiki/Chinese_numerals
        //
        // 中文数字以四个位为一组，从第二个组开始有自己的组名, 名字分别为 `万，亿， 兆`，
        // 其中每组的四个位的名字是 `个十百千`，
        // 阿拉伯数字 0-9 则分别对应 `零` - `九`。
        // 紧接着开始按位转换，转换时每个数字后面紧跟当前位的位名(`个`位不显示)，
        // 每个组的结尾也紧跟组名(第一组省略组名)。

        this.numberAssertEquals( "零", "0");
        this.numberAssertEquals( "五", "5");
        this.numberAssertEquals( "一百二十三", "123");
        this.numberAssertEquals( "一百二十三万四千一百二十三", "1234123");
    }

    public void testZeroDigitMarker()
    {
        // 数字为零的时候省略位名

        this.numberAssertEquals("一百零一", "101");
    }

    public void testLeadingOneBeforeTen()
    {
        // 十位以`一`开头的时候, 可以省略`一`

        this.numberAssertEquals("十", "10");
        this.numberAssertEquals("二百一十三", "213");
        this.numberAssertEquals("一千零一十", "1010");
    }

    public void testNeverHaveConsecutiveZeros()
    {
        // 连续两个零出现的时候, 保留最后一个零

        this.numberAssertEquals("十", "1001");
        this.numberAssertEquals("二百一十三", "1000001");
        this.numberAssertEquals("一千零一十", "320000032");
    }

    public void testGroupMarkerShouldOmitTrailingZeros()
    {
        // 每一组非零的数结尾的零要被省略

        this.numberAssertEquals("一万", "10000");
        this.numberAssertEquals("十万零二百", "100200");
        this.numberAssertEquals("十万零一十", "100010");
        this.numberAssertEquals("十亿", "1000000000");
        this.numberAssertEquals("一千万零一千", "10001000");
        this.numberAssertEquals("十亿零一千", "1000001000");
    }
}
