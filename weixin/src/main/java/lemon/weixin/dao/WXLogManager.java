package lemon.weixin.dao;

import java.util.List;

import lemon.weixin.bean.log.MsgLog;
import lemon.weixin.bean.log.SiteAccessLog;

import org.springframework.stereotype.Repository;

/**
 * This class is for weixin's transaction log
 * @author lemon
 *
 */
@Repository
//TODO convert XML SQL to Annotation
public interface WXLogManager {
	/**
	 * 保存网址接入Log
	 */
	void saveSiteAccessLog(SiteAccessLog log);
	/**
	 * save message log
	 * @param log
	 */
	void saveMessageLog(MsgLog log);
	
	/**
	 * 查询接入日志
	 * @return
	 */
	List<SiteAccessLog> listSiteAccessLogs(int cust_id);
	
	/**
	 * 查询消息日志
	 * @return
	 */
	List<MsgLog> listMsgLogs(int cust_id);
	
	/**
	 * 删除网址接入Log
	 * @param id
	 */
	void deleteSiteAccessLog(int id);
	
	/**
	 * 删除消息日志
	 * @param id
	 */
	void deleteMsgLog(int id);
}
