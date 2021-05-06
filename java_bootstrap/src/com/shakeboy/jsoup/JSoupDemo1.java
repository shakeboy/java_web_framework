package com.shakeboy.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JSoupDemo1 {
    /**
     * jSoup快速入门
     */
    public static void main(String[] args) throws IOException {
        // 1.获取DOM文档对象，根据xml文档获取
        String path = JSoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        // 2.解析xml文档，加载进内存，获取DOM树
        Document document = Jsoup.parse(new File(path), "utf-8");
        System.out.println(document);
        Elements elements = document.getElementsByTag("name");
        // 3.读取
        System.out.println(elements.size());
        for (Element element : elements) {
            System.out.println(element);
        }
        System.out.println("text:"+elements.get(0).text());
    }
    @Test
    public void test() throws IOException {
        URL url = new URL("https://www.bilibili.com/");
        Document document = Jsoup.parse(url, 10000);
        System.out.println(document);
    }
}
