package com.siival.bot.utils;

import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

public class RegexUtilTest {
    @Test
    public void test1() {
        String questionAll = "「風險」的定義是：\n" + //
                "a)保險人有可能變成\n" + //
                "資不抵債的危險性 b)潛在利益有關的不確定性\n" + //
                "c)對可能發生的風險有保護措施 d)潛在損失有關的不確定性";
        questionAll = "testa)testb)testc)testd)test";
        List<String> data = RegexUtil.getMatches(RegexUtil.REGEX_QUESTION1, questionAll);
        System.out.println(data.size());
        System.out.println(data);
        String input = "(test)";
        String test = Pattern.quote(input);
        System.out.println(test);
    }
}
