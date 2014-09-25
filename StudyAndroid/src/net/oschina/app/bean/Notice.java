package net.oschina.app.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;
import net.oschina.app.AppException;
import net.oschina.app.common.StringUtils;



/**
 * 通知信息实体类
 * author:syf
 * time:20140915
 */
public class Notice implements Serializable{
	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT = "oschina";
	
	public final static int TYPE_ATME = 1;
	public final static int TYPE_MESSAGE = 2;
	public final static int TYPE_COMMENT = 3;
	public final static int TYPE_NEFFAN = 4;
	
	private int atmeCount;
	private int msgCount;
	private int reviewCount;
	private int newFansCount;
	
	public int getAtmeCount() {
		return atmeCount;
	}
	public void setAtmeCount(int atmeCount) {
		this.atmeCount = atmeCount;
	}
	public int getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getNewFansCount() {
		return newFansCount;
	}
	public void setNewFansCount(int newFansCount) {
		this.newFansCount = newFansCount;
	}
	
	
	public static Notice parse(InputStream inputStream) throws IOException, AppException{
		
		Notice notice = null;
		XmlPullParser xmlPullParser = Xml.newPullParser();
		
		try {
			xmlPullParser.setInput(inputStream, UTF8);
			int evtType = xmlPullParser.getEventType();
			while(evtType != XmlPullParser.END_DOCUMENT){
				String tag = xmlPullParser.getName();
				switch (evtType) {
				case XmlPullParser.START_TAG:
					if(tag.equalsIgnoreCase("notice"))
					{
						notice = new Notice();
					}
					else if(notice != null)
					{
						if(tag.equalsIgnoreCase("atmeCount"))
						{
							notice.setAtmeCount(StringUtils.toInt(xmlPullParser.nextText(), 0));
						}
						else if(tag.equalsIgnoreCase("msgCount"))
						{
							notice.setMsgCount(StringUtils.toInt(xmlPullParser.nextText(), 0));
						}
						else if(tag.equalsIgnoreCase("reviewCount"))
						{
							notice.setReviewCount(StringUtils.toInt(xmlPullParser.nextText(), 0));
						}
						else if(tag.equalsIgnoreCase("newFansCount"))
						{
							notice.setNewFansCount(StringUtils.toInt(xmlPullParser.nextText(), 0));
						}
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				evtType = xmlPullParser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			throw AppException.xml(e);
		}finally{
			inputStream.close();
		}
		
		return notice;
	}
	
	
	
	
	
	
	
	
}
