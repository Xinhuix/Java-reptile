package com.example.webcrawlerspringbootstarter.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @use 获取指定HTML标签的指定属性的值
 * @FullName com.mmq.regex.MatchHtmlElementAttrValue.java </br>
 * @JDK 1.6.0 </br>
 * @Version 1.0 </br>
 */
public class MatchHtmlElementAttrValue {

    /**
     * 获取指定HTML标签的指定属性的值
     * @param source 要匹配的源文本
     * @param element 标签名称
     * @param attr 标签的属性名称
     * @return 属性值列表
     */
    public static Set<String> match(String source, String element, String attr) {
        Set<String> result = new HashSet<>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        Iterator<String> iterator = result.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            int html = next.indexOf("html");
            if (html == -1){
                iterator.remove();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String source = "<a title=中国体育报 href=''>aaa</a><a title='北京日报' href=''>bbb</a>";
        Set<String> list = match(source, "a", "title");
        System.out.println(list);
    }
}