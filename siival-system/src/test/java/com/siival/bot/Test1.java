package com.siival.bot;

import org.junit.Test;

/**
 * @author mark acrossxwall@gmail.com
 * @version 1.0.0
 * @ClassName TestRedis
 * 
 * @Date 2022-03-21 16:30
 */
public class Test1 {
    @Test
    public void testChapter() {
        String chapter = "1.1.1";
        String[] chapters = chapter.split("\\.");
        System.out.println(chapters[0]);
    }

}
