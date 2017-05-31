package com.assassin.common;

import java.util.Map;

import com.assassin.utils.StringUtils;
import com.assassin.utils.PropertiesLoader;
import com.google.common.collect.Maps;

/**
 * 全局配置类
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/////////////////////////////////////////////////////////
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	/////////////////////////////////////////////////////////
	
	// 显示/隐藏
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	// 是/否
	public static final String YES = "1";
	public static final String NO = "0";
	
	// 对/错
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	public static final String REST_RESPONSE_SPLIT = "♀";
	
	public static final String REST_METHOD_GET = "GET";
	public static final String REST_METHOD_POST = "POST";
	public static final String REST_METHOD_PUT = "PUT";
	public static final String REST_METHOD_DELETE = "DELETE";

	//打印情况状态
	public static final String PRINTINFO_0="0";//正常打印
	public static final String PRINTINFO_1="1";//根据返回结果(desc)强制提示，提示完成直接终止。
	public static final String PRINTINFO_2="2";//根据返回结果(desc)可选提示提示， desc+是否继续？ 是弹出打印申请界面，否终止
	public static final String PRINTINFO_3="3";//弹框状态

	/**
	 * 权调库查询类型
	 */
	public static final String REGSURVEY_TYPE_TERRA="1"; //纯土地
	public static final String REGSURVEY_TYPE_HOUSR="2"; //实测房地
	public static final String REGSURVEY_TYPE_PREHOUSR="3"; //预测房地
	public static final String REGSURVEY_TYPE_PREHOUSR_PLEDGE="4"; //在建工程抵押
	public static final String REGSURVEY_TYPE_REHOUSE_PREGISTER="5"; //预告登记

	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

}
