package com.dsys.common.sdk.sms.properties;

/**
 * Title: InlineFile.java Description: 内嵌类型
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:34:18
 * @update 2019年12月14日 下午12:34:18
 * @version 1.0
 */

public class InlineFile extends MailType{

	private String filePath;
	
	private String cid;

	public InlineFile(String filePath, String cid) {
		super();
		this.filePath = filePath;
		this.cid = cid;
	}

	public InlineFile() {
		super();
	}

	@Override
	public char getType() {
		return MailType.TYPE_FILE;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}
