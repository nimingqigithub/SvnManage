package com.assassin.utils;


import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Map;

/**
 * Created by nimq on 2015/8/26.
 */
public class XmlUtils {

    /**
     * 创建获取配号服务XML对象
     * @param mapCertiNum
     * @param rootName
     * @return
     */
    public static Document CreateDocumentByMap(Map<String,String> mapCertiNum,String rootName){
        Document document=DocumentHelper.createDocument();
        Element element= document.addElement(rootName); //创建根元素
        for(String key : mapCertiNum.keySet()){
            String value=mapCertiNum.get(key);
            Element elementChild=element.addElement(key);
            elementChild.addCDATA(value); //创建CDATA对象，处理特殊字符
        }
        return document;
    }

    /**
     * 创建获取配号服务批量XML对象
     * @param elementParent
     * @param rootName
     * @param mapCertiNum
     * @return
     */
    public static Element CreateElementByMap(Element elementParent,String rootName,Map<String,String> mapCertiNum){
        Element element= elementParent.addElement(rootName); //创建根元素
        for(String key : mapCertiNum.keySet()){
            String value=mapCertiNum.get(key);
            Element elementChild=element.addElement(key);
            elementChild.addCDATA(value); //创建CDATA对象，处理特殊字符
        }
        return element;
    }
    /**
     * 把XML字符串解析成document对象
     * @param strText
     * @return
     * @throws Exception
     */
    public static Document CreateDocumentByString(String strText) throws Exception {
        Document document=DocumentHelper.parseText(strText);
        return document;
    }

    /**
     * 把本地XML文件解析成Document对象
     * @param path
     * @return
     * @throws Exception
     */
    public static Document CreateDocumentByPath(String path) throws  Exception{
        SAXReader saxReader = new SAXReader();
        Document document = null;
        document = saxReader.read(new File(path));
        return document;
    }

}



