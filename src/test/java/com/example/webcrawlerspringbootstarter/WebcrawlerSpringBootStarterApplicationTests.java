/*
package com.example.webcrawlerspringbootstarter;

import com.example.webcrawlerspringbootstarter.utils.WebCrawlerUtil;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WebcrawlerSpringBootStarterApplicationTests {

    public final static String wp = "//*[@id=\"link-report\"]/p";

    public final static String DOUBAN_MOVIE_URL_XPATH = "//*[@class=\"entry-title\"]/a";

    //导演
    public final static String MOVILE_DIRECTOR = "//*[@class=\"entry-content u-clearfix\"]/p";

    public final static String MOVILE_NAME = "//*[@class=\"entry-title\"]";

    public final static String DOUBAN_MOVIE_SCORE_XPATH = "//*[@id=\"interest_sectl\"]/div[1]/div[2]/strong";


    @Test
    public void contextLoads() throws Exception {
        String s1 = readTxt("C:\\new.txt");
        // List<String> list = WebCrawlerUtil.parseList(s, MOVILE_DIRECTOR);
        List<String> list = WebCrawlerUtil.parseList(s1, MOVILE_NAME);
        String[] split = list.get(0).split("]");
        String substring = split[0].substring(1);
        //  System.out.println( new String(s.getBytes("UTF-8"),"UTF-8"));

      */
/*  Set<String> list = MatchHtmlElementAttrValue.match(s, "a", "href");
        for (String s1 : list) {
            System.out.println(s1);
        }*//*

    }

    @Test
    public void test() throws Exception {
        String s1 = readTxt("C:\\new1.txt");
        System.out.println(s1);
    }

    @Test
    public  void test2() throws IOException {
        File file = new File("C:\\new1.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
        char[] chars = new char[1024];
        String content = "";
        while (inputStreamReader.read(chars) > 0 ) {
            content += new String( chars );
        }
        String[] split = content.split("\\r");
        System.out.println(split[0].contains("导演"));
        System.out.println(split.length);


    }


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


*/
