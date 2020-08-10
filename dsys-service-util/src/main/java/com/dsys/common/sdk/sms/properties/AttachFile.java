package com.dsys.common.sdk.sms.properties;

/**
 * Title: AttachFile.java Description: 附件类型
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午12:35:18
 * @update 2019年12月14日 下午12:35:18
 * @version 1.0
 */

public class AttachFile extends MailType {

	private String filePath;

	private String fileName;

	@Override
	public char getType() {
		return TYPE_ATTACH;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
