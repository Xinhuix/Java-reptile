package com.example.webcrawlerspringbootstarter.utils;

import com.example.webcrawlerspringbootstarter.constant.DoubanConstant;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import java.util.ArrayList;
import java.util.List;

public class WebCrawlerUtil {

    public static String downloadPageContext(String url) {
        return HttpClientUtil.get(url);
    }

    public static List<String> parse(String body, String xPath) {
        Object[] objects = baseParse(body,xPath);
        List<String> list = new ArrayList<>();
        if (objects != null) {
            for (Object object : objects) {
                list.add(((TagNode) object).getText().toString());
            }
        }
        return list;
    }

    public static List<String> parseAttr(String body, String xPath,String nodeAttr) {
        Object[] objects = baseParse(body,xPath);
        List<String> list = new ArrayList<>();
        if (objects != null) {
            for (Object object : objects) {
                list.add(((TagNode) object).getAttributeByName(nodeAttr));
            }
        }
        return list;
    }

    public static String parseOne(String body, String xPath) {
        List<String> parse = parse(body, xPath);
        if(parse.size() == 1){
            return parse.get(0);
        }
        return "";
    }

    public static List<String> parseList(String body, String xPath) {
        return parse(body, xPath);
    }

    private static Object[] baseParse(String body, String xPath){
        HtmlCleaner cleaner = new HtmlCleaner();
        TagNode clean = cleaner.clean(body);
        Object[] objects = null;
        try {
            objects = clean.evaluateXPath(xPath);
        } catch (XPatherException e) {
            e.printStackTrace();
        }
        return objects;
    }


}
