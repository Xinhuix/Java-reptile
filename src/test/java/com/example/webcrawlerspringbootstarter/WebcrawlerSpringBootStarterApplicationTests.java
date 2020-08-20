package com.example.webcrawlerspringbootstarter;

import com.example.webcrawlerspringbootstarter.constant.DoubanConstant;
import com.example.webcrawlerspringbootstarter.utils.WebCrawlerUtil;
import org.junit.Test;

import java.io.*;

public class WebcrawlerSpringBootStarterApplicationTests {

    public final static String wp = "//*[@id=\"link-report\"]/p";

    @Test
    public void contextLoads() throws Exception {
        String s = readTxt("D:\\new122.txt");
        String name = WebCrawlerUtil.parseOne(s, wp);
        System.out.println(name);
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


