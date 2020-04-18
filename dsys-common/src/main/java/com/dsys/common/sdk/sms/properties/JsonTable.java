package com.dsys.common.sdk.sms.properties;

/**
 * Title: JsonTable.java Description: 描述
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:36:18
 * @update 2019年12月14日 下午12:36:18
 * @version 1.0
 */

public class JsonTable extends MailType {

	private String title;
	
	private String data;
	
	private String formatPath;

	public JsonTable(String title, String data) {
		super();
		this.title = title;
		this.data = data;
	}

	public JsonTable(String title, String data, String formatPath) {
		super();
		this.title = title;
		this.data = data;
		this.formatPath = formatPath;
	}

	public JsonTable() {
		super();
	}

	@Override
	public char getType() {
		return MailType.TYPE_JSON;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFormatPath() {
		return formatPath;
	}

	public void setFormatPath(String formatPath) {
		this.formatPath = formatPath;
	}

}
