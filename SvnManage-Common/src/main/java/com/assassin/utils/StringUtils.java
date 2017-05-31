/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.assassin.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.io.DefaultResourceLoader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    
    /**
     * 转换为字节数组
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
    }
    
    /**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs){
    	if (str != null){
        	for (String s : strs){
        		if (str.equals(trim(s))){
        			return true;
        		}
        	}
    	}
    	return false;
    }
    
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * @param html
	 * @return
	 */
	public static String replaceMobileHtml(String html){
		if (html == null){
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;
		char temp;
		boolean isCode = false; // 是不是HTML代码
		boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
		for (int i = 0; i < param.length(); i++) {
			temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if (temp == '>' && isCode) {
				n = n - 1;
				isCode = false;
			} else if (temp == ';' && isHTML) {
				isHTML = false;
			}
			try {
				if (!isCode && !isHTML) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		// 取出截取字符串中的HTML标记
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)",
				"$1$2");
		// 去掉不需要结素标记的HTML标记
		temp_result = temp_result
				.replaceAll(
						"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
						"");
		// 去掉成对的HTML标记
		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>",
				"$2");
		// 用正则表达式取出标记
		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		// 补全不成对的HTML标记
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append(endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}

	
	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	/**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    /**
	 * 驼峰命名法工具
	 * @return
	 * 		toCamelCase("hello_world") == "helloWorld" 
	 * 		toCapitalizeCamelCase("hello_world") == "HelloWorld"
	 * 		toUnderScoreCase("helloWorld") = "hello_world"
	 */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }
    
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
		String projectPath = "";
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }


	/**
	 * 根据classpathfilename查询文件路径
	 * @param classpathfilename classpath 下面的文件路径及名称
	 * @return 文件的绝对路径
	 * @throws NullPointerException 找不到报空指针
     */
	public static String getClasspathfile(String classpathfilename) throws NullPointerException{
		return  StringUtils.class.getResource(classpathfilename).getFile();
	}
    
    /**
     * 如果不为空，则设置值
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)){
			target = source;
		}
	}
 
    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     * @param objectString 对象串
     *   例如：row.user.id
     *   返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString){
    	StringBuilder result = new StringBuilder();
    	StringBuilder val = new StringBuilder();
    	String[] vals = split(objectString, ".");
    	for (int i=0; i<vals.length; i++){
    		val.append("." + vals[i]);
    		result.append("!"+(val.substring(1))+"?'':");
    	}
    	result.append(val.substring(1));
    	return result.toString();
    }

	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s){
		if(null==s || "null".equalsIgnoreCase(s) || "".equals(s)){
			return  true;
		}
		return  false;
	}
	/**
	 * shixj 2016.7.14
	 * 删除字符串中所有与指定字符相同的字符（删除右边）
	 * @param contentStr
	 * @param targetStr
	 * @param isTrimAll 是否从末尾删除至不为指定字符串
	 * @return
	 */
	public static String trimRight(String contentStr,String targetStr,Boolean isTrimAll){
		String resStr ;
		if(targetStr==null||targetStr.length()==0||contentStr==null||contentStr.length()==0){
			resStr=contentStr;
		}else {
			while(true){
				if(contentStr.substring(contentStr.length()-targetStr.length()).equals(targetStr)){
					contentStr = contentStr.substring(0,contentStr.length()-targetStr.length());
					if(!isTrimAll){
						resStr=contentStr;
						break;
					}
				}else {
					resStr=contentStr;
					break;
				}
			}
		}
		return  resStr;
	}
	/**
	 * shixj 2016.7.14
	 * 删除字符串中所有与指定字符相同的字符（删除左边）
	 * @param contentStr
	 * @param targetStr
	 * @param isTrimAll 是否从末尾删除至不为指定字符串
	 * @return
	 */
	public static String trimLeft(String contentStr,String targetStr,Boolean isTrimAll){
		String resStr ;
		if(targetStr==null||targetStr.length()==0||contentStr==null||contentStr.length()==0){
			resStr=contentStr;
		}else {
			while(true){
				if(contentStr.substring(0,targetStr.length()).equals(targetStr)){
					contentStr = contentStr.substring(targetStr.length(),contentStr.length());
					if(!isTrimAll){
						resStr=contentStr;
						break;
					}
				}else {
					resStr=contentStr;
					break;
				}
			}
		}
		return  resStr;
	}
	/**
	 * shixj 2016.7.14
	 * 删除字符串中所有与指定字符相同的字符（中间不删除）
	 * @param contentStr
	 * @param targetStr
	 * @param isTrimAll 是否从末尾删除至不为指定字符串
	 * @return
	 */
	public static String trimLeftAndRight(String contentStr,String targetStr,Boolean isTrimAll){
		String resStr ;
		resStr=trimRight(trimLeft(contentStr,targetStr,isTrimAll),targetStr,isTrimAll);
		return  resStr;
	}
	/**
	 * shixj 2016.7.14
	 * 删除字符串中所有与指定字符相同的字符（不论位置）
	 * @param contentStr
	 * @param targetStr
	 * @return
	 */
	public static String trimAnyLocation(String contentStr,String targetStr){
		String resStr ;
		if(targetStr==null||targetStr.length()==0||contentStr==null||contentStr.length()==0){
			resStr=contentStr;
		}else {
			int i=0;
			while(true){
				if((i+targetStr.length()<=contentStr.length())&&contentStr.substring(i,i+targetStr.length()).equals(targetStr)){
					contentStr =contentStr.substring(0,i)+ contentStr.substring(i+targetStr.length(),contentStr.length());
				}else {
					if(i+targetStr.length()>contentStr.length())
					{
						resStr=contentStr;
						break;
					}
					i++;
				}
			}
		}
		return  resStr;
	}
	/**
	 * shixj 2016.8.17
	 * 判断equals之前判断字符串是否为空
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static Boolean equalsWithoutNull(String str1 ,String str2){
		return str1!=null&&str2!=null&&str1.equals(str2);
	}
	/**
	 * shixj 2017.1.11
	 * 多个字符串转成List
	 */
	public static List<String> getList(String  ...s){
		List<String>res = new ArrayList<>();
		for (String i : s){
			res.add(i);
		}
		return res;
	}
	/**
	 *
	 * @param  maps  需要替换的key 和 value
	 * @param template 模板内容
	 * @return
	 */
	public static String replaceTemplateContent(List<Map<String,String>> maps,String template){
		StringBuilder replacetemplate = null;
		String content = template;
		if(!maps.isEmpty()){
			replacetemplate = new StringBuilder();
			for (Map map : maps) {
				Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					content = content.replaceAll(entry.getKey(), entry.getValue());
				}
				replacetemplate.append(content).append("\n");
				content=template;
			}

		}
		return replacetemplate.toString();
	}
	/**
	 * shixj 2017.04.10
	 * 针对sql直接复制多端String,使用StringBulder.append方法拼接，减少内存消耗
	 * @param s
	 * @return
	 */
	public static String getStr(String ...s){
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i <s.length ; i++) {
			sb.append(s[i]);
		}
		return sb.toString();
	}
	/**
	 * shixj 2016.8.17
	 * 字符串为空时返回nullReplaceStr否则返回str
	 * @param str
	 * @param nullReplaceStr
	 * @return
	 */
	public static String nullToAny(String str ,String nullReplaceStr){
		if(str == null || str.length() == 0){
			return nullReplaceStr==null?"":nullReplaceStr;
		}else return str;
	}
}
