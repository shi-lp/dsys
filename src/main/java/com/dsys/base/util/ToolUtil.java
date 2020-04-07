package com.dsys.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Title: ToolUtil.java Description: 工具类
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月11日 下午9:11:59
 * @update 2019年12月11日 下午9:11:59
 * @version 1.0
 */

public class ToolUtil {

	public static boolean isNullOrEmpty(String[] str) {
		if (str == null || str.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Long longTime) {
		if (longTime == null || longTime.longValue() <= 0L) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null || "".equals(obj)) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Object[] obj) {
		if (obj == null || obj.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(List<?> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Map<Object, Object> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(Set<?> set) {
		if (set == null || set.isEmpty()) {
			return true;
		}
		return false;
	}

	public static String changeIP(String ip) {
		String[] ips = ip.split("\\.");
		StringBuffer str = new StringBuffer();
		String dot = ".";
		for (int i = 0; i < ips.length; i++) {
			int temp = Integer.parseInt(ips[i]);

			if (i == ips.length - 1) {
				str.append(temp);
			} else {
				str.append(temp).append(dot);
			}
		}
		return str.toString();
	}

	public static String getPcMessager() {
		String result = "";
		try {
			File file = File.createTempFile("realhowto", ".vbs");
			file.deleteOnExit();
			FileWriter fw = new FileWriter(file);
			String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\nSet colItems = objWMIService.ExecQuery _ \n   (\"Select * from Win32_BaseBoard\") \nFor Each objItem in colItems \n    Wscript.Echo objItem.SerialNumber \n    exit for  ' do the first cpu only! \nNext \n";
			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				result = String.valueOf(result) + line;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.trim();
	}

}
