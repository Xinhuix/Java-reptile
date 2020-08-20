package com.example.webcrawlerspringbootstarter;

import com.example.webcrawlerspringbootstarter.utils.MatchHtmlElementAttrValue;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.Set;

public class WebcrawlerSpringBootStarterApplicationTests {

    public final static String wp = "//*[@id=\"link-report\"]/p";

    public final static String DOUBAN_MOVIE_URL_XPATH = "//*[@class=\"entry-title\"]/a";

    @Test
    public void contextLoads() throws Exception {
        String s = readTxt("E:\\new1.txt");
        Set<String> list = MatchHtmlElementAttrValue.match(s, "a", "href");
        for (String s1 : list) {
            System.out.println(s1);
        }
    }

//    public final static String DOUBAN_MOVIE_NAME_XPATH = "//*[@id=\"content\"]/h1/span[1]";


    //	读取文件
    public static String readTxt(String path) throws Exception {

        StringBuilder strb = new StringBuilder("");

        InputStream is = new FileInputStream(new File(path));
        InputStreamReader isr = new InputStreamReader(is, getCode(path));
        BufferedReader br = new BufferedReader(isr);

        String str = "";
        while (null != (str = br.readLine())) {
            strb.append(str);
            strb.append("\r\n");
        }
        br.close();
        return strb.toString();
    }

    // 获取编码格式 gb2312,UTF-16,UTF-8,Unicode,UTF-8
    public static String getCode(String path) throws Exception {
        InputStream inputStream = new FileInputStream(path);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "gb2312"; // 或GBK
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        else if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";
        inputStream.close();
        return code;
    }
}


