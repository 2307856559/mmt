package lemon.yixin.bean.message;

import lemon.shared.xstream.annotations.XStreamCDATA;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Text message
 * 
 * @author lemon
 * @version 1.0
 * 
 */
@XStreamAlias("xml")
public class TextMessage extends YiXinMessage {
	/** Content */
	@XStreamAlias("Content")
	@XStreamCDATA
	private String content;

	public TextMessage(){
		super(MsgType.TEXT);
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
