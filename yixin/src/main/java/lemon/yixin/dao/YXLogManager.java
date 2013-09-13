package lemon.yixin.dao;

import java.util.List;

import lemon.yixin.bean.log.MsgLog;
import lemon.yixin.bean.log.SiteAccessLog;
import lemon.yixin.bean.log.SubscribeLog;
import lemon.yixin.bean.log.UnSubscribeLog;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * This class is for YiXin's log
 * 
 * @author lemon
 * @version 1.0
 * 
 */
@Repository
public interface YXLogManager {
	/**
	 * Save site access log
	 * 
	 * @param log
	 */
	@Insert("INSERT INTO yixin_log_siteaccess(cust_id,signature,timestamp,nonce,echostr,token) SELECT #{cust_id},#{signature},#{timestamp},#{nonce},#{echostr},#{token}")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void saveSiteAccessLog(SiteAccessLog log);

	/**
	 * save message log
	 * 
	 * @param log
	 */
	@Insert("INSERT INTO yixin_log_msg(cust_id,msgType,msg) SELECT #{cust_id},#{msgType},#{msg}")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void saveMessageLog(MsgLog log);
	
	/**
	 * save subscribe log
	 * @param log
	 */
	@Insert("INSERT INTO yixin_log_subscribe(cust_id,yxid) SELECT #{cust_id},#{yxid}")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void saveSubscribeLog(SubscribeLog log);
	
	/**
	 * save unsubscribe log
	 * @param log
	 */
	@Insert("INSERT INTO yixin_log_unsubscribe(cust_id,yxid) SELECT #{cust_id},#{yxid}")
	void saveUnSubscribeLog(UnSubscribeLog log);

	/**
	 * query for site access log 
	 * 
	 * @return
	 */
	// TODO query for site access log
	List<SiteAccessLog> listSiteAccessLogs(int cust_id);

	/**
	 * query for message log
	 * 
	 * @return
	 */
	// TODO query for message log
	List<MsgLog> listMsgLogs(int cust_id);

	/**
	 * delete site access log
	 * 
	 * @param id
	 */
	@Delete("DELETE FROM yixin_log_siteaccess WHERE id=#{id}")
	void deleteSiteAccessLog(int id);

	/**
	 * delete message log
	 * 
	 * @param id
	 */
	@Delete("DELETE FROM yixin_log_msg WHERE id=#{id}")
	void deleteMsgLog(int id);
}
