package com.dsys.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringTokenizer;

public class StringUtils {

	/**
	 * 
	 * @param str
	 * @return 如果字符串为空或者长度为0，返回true，否则返回false
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 
	 * @param c
	 * @return 如果字符序列为空或者长度为0，返回true，否则返回false
	 */
	public static boolean isEmpty(CharSequence c) {
		return (c == null || c.length() == 0);
	}

	/**
	 * 
	 * @param c
	 * @return 如果字符序列为空，返回0，否则返回字符序列的长度
	 */
	public static int length(CharSequence c) {
		return c == null ? 0 : c.length();
	}

	/**
	 * 用utf-8编码
	 * 
	 * @param str 字符串
	 * @return 返回一个utf8的字符串
	 */
	public static String utf8Encode(String str) {
		if (!isEmpty(str) || str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("不被支持的编码类型", e);
			}
		}
		return str;
	}


	public static boolean isNotBlank(String str) {
		return str != null && str.trim().length() > 0;
	}

	/**
	 * 
	 * @param text	源字符串
	 * @param caseWord 分隔符
	 * @return 通过自定义分隔符
	 */
	public static final String[] StrByCaseWordToArray(String text,String caseWord){
		if (isBlank(text)) {
			return new String[0];
		}
		StringTokenizer tokens = new StringTokenizer(text, caseWord);
		String[] words = new String[tokens.countTokens()];
		for (int i = 0; i < words.length; i++) {
			words[i] = tokens.nextToken().toLowerCase();
		}
		return words;
	}

	public static String StrArray2Str(String[] args){
		return String.join(",",args);
	}

	public static void main(String[] args) {
		System.out.println(StrArray2Str(new String[]{"1","2","3"}));;
	}
	
}
