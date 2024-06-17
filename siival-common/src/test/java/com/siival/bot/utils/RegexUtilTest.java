package com.siival.bot.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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

        String str = "select * from order where createdUser = ${currentUser} and  depart = ${currentOrg} and status = 'VALID'";
        String reg = "\\$\\{[a-zA-Z0-9]+\\}";//定义正则表达式
 
        Pattern patten = Pattern.compile(reg);//编译正则表达式
        Matcher matcher = patten.matcher(str);// 指定要匹配的字符串
 
        List<String> matchStrs = new ArrayList<>();
 
        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
           matchStrs.add(matcher.group());//获取当前匹配的值
        }
 
        for (int i = 0; i < matchStrs.size(); i++) {
            System.out.println(matchStrs.get(i));
        }
    }
}
