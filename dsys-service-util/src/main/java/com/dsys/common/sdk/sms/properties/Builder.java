package com.dsys.common.sdk.sms.properties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Title: Builder.java Description: 描述
 * 
 * @author shilp Company: Copyright: Copyright (c) 2019
 * @created 2019年12月14日 下午3:32:28
 * @update 2019年12月14日 下午3:32:28
 * @version 1.0
 */
@Data
@Component
public class Builder {
	
	String from;
	
	String fromName;
	
	List<String> to = new ArrayList<String>();
	
	List<String> cc = new ArrayList<String>();
	
	String subject;

	public Builder() {

	}

	/**
	 * 添加发送人信息，为空则需要调用方主动设置
	 * 
	 * @param from
	 *            发送人邮件字符串
	 * @return
	 */
	public Builder from(String from) {
		this.from = from;
		return this;
	}

	/**
	 * 添加发送人信息，为空则需要调用方主动设置
	 * 
	 * @param from
	 *            发送人邮件字符串
	 * @return
	 */
	public Builder fromName(String fromName) {
		this.fromName = fromName;
		return this;
	}

	/**
	 * 添加收件人
	 * 
	 * @param toAddr
	 *            String
	 * @return
	 */
	public Builder addTo(String toAddr) {
		to.add(toAddr);
		return this;
	}

	/**
	 * 添加收件人列表
	 * 
	 * @param toAddr
	 *            String
	 * @return
	 */
	public Builder addTo(List<String> toAddr) {
		to.addAll(toAddr);
		return this;
	}

	/**
	 * 设置收件人列表
	 * 
	 * @param to
	 *            收件人数组
	 * @return
	 */
	public Builder to(List<String> to) {
		this.to = to;
		return this;
	}

	/**
	 * 添加抄送人
	 * 
	 * @param ccAddr
	 * @return
	 */
	public Builder addCc(String ccAddr) {
		cc.add(ccAddr);
		return this;
	}

	/**
	 * 添加抄送人列表
	 * 
	 * @param ccAddr
	 * @return
	 */
	public Builder addCc(List<String> ccAddr) {
		cc.addAll(ccAddr);
		return this;
	}

	/**
	 * 设置抄送人列表
	 * 
	 * @param cc
	 * @return
	 */
	public Builder cc(List<String> cc) {
		this.cc = cc;
		return this;
	}

	/**
	 * 设置主题
	 * 
	 * @param subject
	 * @return
	 */
	public Builder subject(String subject) {
		this.subject = subject;
		return this;
	}

	/**
	 * 生成MailMessage
	 * 
	 * @return MailMessage
	 */
	public MailProperties build() {
		if (to.size() < 1){
			throw new IllegalStateException("邮件接收人为空!");
		}
		return new MailProperties(this);
	}
}
