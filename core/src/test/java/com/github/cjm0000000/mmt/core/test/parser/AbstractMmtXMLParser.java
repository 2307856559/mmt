package com.github.cjm0000000.mmt.core.test.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import static org.junit.Assert.*;

import com.github.cjm0000000.mmt.core.MmtException;
import com.github.cjm0000000.mmt.core.ServiceType;
import com.github.cjm0000000.mmt.core.config.MmtCharset;
import com.github.cjm0000000.mmt.core.message.Message;
import com.github.cjm0000000.mmt.core.parser.MmtXMLParser;
import com.github.cjm0000000.mmt.core.test.base.MmtTestBase;

/**
 * XML Tester template
 * @author lemon
 * @version 2.0
 *
 */
public abstract class AbstractMmtXMLParser extends MmtTestBase {

	/**
	 * generate specific XML node
	 * @param sb
	 * @param original
	 */
	protected abstract void generateSpecXMLNodes(StringBuilder sb, Message original);
	
	/**
	 * verify specific fields
	 * @param after
	 * @param before
	 */
	protected abstract void verifySpecFields(Message after, Message before);
	
	/**
	 * get message instance and set specific fields
	 * @return
	 */
	protected abstract Message getMsgInstance();
	
	/**
	 * Run the test suite
	 */
	@Test
	public void run(){
		ServiceType[] services = ServiceType.values();
		for (ServiceType serviceType : services) 
			parser(serviceType);
	}
	
	/**
	 * parser message
	 * @param service_type
	 */
	protected final void parser(ServiceType service_type){
		Message original = getMsgInstance();
		//set common values
		setCommonFields(service_type, original);
		//build XML string
		StringBuilder sb = new StringBuilder();
		generateCommonXMLNodes(sb, original);
		generateSpecXMLNodes(sb, original);
		sb.append("</xml>");
		//parser
		InputStream is = makeIS(sb.toString());
		Message after = MmtXMLParser.fromXML(service_type, is);
		//verify
		verifyCommonFields(after, original);
		verifySpecFields(after, original);
	}
	
	/**
	 * make a input stream from string
	 * @param msg
	 * @return
	 */
	private InputStream makeIS(String msg){
		try {
			return new ByteArrayInputStream(msg.getBytes(MmtCharset.LOCAL_CHARSET));
		} catch (UnsupportedEncodingException e) {
			throw new MmtException("Unsupported Encoding.", e.getCause());
		}
	}
	
	/**
	 * verify common fields
	 * @param after the message after parser
	 * @param before the original message
	 */
	private void verifyCommonFields(Message after, Message before){
		assertNotNull(after);
		assertNotNull(before);
		assertEquals(after.getCreateTime(), before.getCreateTime());
		assertEquals(after.getFromUserName(), before.getFromUserName());
		assertEquals(after.getMsgId(), before.getMsgId());
		assertEquals(after.getMsgType(), before.getMsgType());
		assertEquals(after.getToUserName(), before.getToUserName());
	}
	
	/**
	 * set the message's fields with initialize value
	 * @param service_type
	 * @param original
	 */
	private void setCommonFields(ServiceType service_type, Message original){
		original.setCreateTime(String.valueOf(System.currentTimeMillis()/1000));
		original.setCust_id(CUST_ID);
		original.setFromUserName("ot9x4jpm4x_rBrqacQ8hzikL9D-M");
		original.setId(0);
		original.setMsgId(5939685126051988740L);
		original.setService_type(service_type);
		original.setToUserName("gh_de370ad657cf");
	}
	
	/**
	 * generate common XML nodes
	 * @param sb
	 * @param original
	 */
	private void generateCommonXMLNodes(StringBuilder sb, Message original){
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+original.getToUserName()+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+original.getFromUserName()+"]]></FromUserName>");
		sb.append("<CreateTime>"+original.getCreateTime()+"</CreateTime>");
		sb.append("<MsgType><![CDATA["+original.getMsgType()+"]]></MsgType>");
		sb.append("<MsgId>"+original.getMsgId()+"</MsgId>");
	}
}
