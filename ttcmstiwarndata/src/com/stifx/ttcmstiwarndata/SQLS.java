package com.stifx.ttcmstiwarndata;

public class SQLS {
	
	public static String sqlQueryReplica = "SELECT USERS.LOGIN " +
			", USERS.NAME AS USERNAME " +
			", USERS.BALANCE " +
			", USERS.EQUITY " +
			", CAST( CAST( SUM(TRS.PROFIT) AS decimal(10,2) ) - USERS.EQUITY  AS decimal(10,2) ) AS LOSS_EQUITY " +
			", CAST( CAST( SUM(TRS.PROFIT) AS decimal(10,2) ) * " + CONSTANTS.RISK_EQUITY_RATE + " AS decimal(10,2) ) AS WARNING_EQUITY " +
			", CAST( SUM(TRS.PROFIT) AS decimal(10,2) ) AS PURE_DEPOSIT " +
			", USERS.MODIFY_TIME " +
			"FROM report_live.MT4_USERS USERS  " +
			"INNER JOIN report_live.MT4_TRADES TRS  " +
			"ON USERS.LOGIN = TRS.LOGIN  " +
			"AND TRS.CMD = 6 " +
			"GROUP BY USERS.LOGIN " +
			", USERS.NAME " + 
			", USERS.EQUITY " +
			", USERS.MODIFY_TIME " +
			"HAVING SUM(TRS.PROFIT) > 0  " +
			"AND USERS.EQUITY <= WARNING_EQUITY " +
			"AND PURE_DEPOSIT > 0";
	
	
	public static String deleteTmpSql = "DELETE FROM sti_warn.WarnListTmp WHERE broker_id = " + CONSTANTS.BROKER_ID;
	
	public static String insertTmpSql = "INSERT INTO sti_warn.WarnListTmp " + 
			"(  " + 
			"	broker_id, " + 
			"	mt4Login, " + 
			"	username, " + 
			"	tel, " + 
			"	email, " + 
			"	balance, " + 
			"	equity, " + 
			"	lossEquity, " + 
			"	warningEquity, " + 
			"	pureDeposit, " + 
			"	totalWarnCount, " + 
			"   recentModifyTime, " +
			"	enterTime, " + 
			"	status " + 
			") " + 
			"values " + 
			"( " + 
			"	" + CONSTANTS.BROKER_ID + ", " + 
			"	?, " + 
			"	?, " + 
			"	'', " + 
			"	'', " + 
			"	?, " + 
			"	?, " + 
			"	?, " + 
			"	?, " + 
			"	?, " + 
			"	0, " + 
			"	?, " + 
			"	?, " + 
			"	'New' " + 
			")";
	
	
	public static String insertLstSqlNew = "INSERT INTO sti_warn.WarnList " + 
			"(  " + 
			"	broker_id, " + 
			"	mt4Login, " + 
			"	username, " + 
			"	tel, " + 
			"	email, " + 
			"	balance, " + 
			"	equity, " + 
			"	lossEquity, " + 
			"	warningEquity, " + 
			"	pureDeposit, " + 
			"	totalWarnCount, " + 
			"   recentModifyTime, " +
			"	enterTime, " + 
			"	status " + 
			") " + 
			"SELECT " +
			"	broker_id, " + 
			"	mt4Login, " + 
			"	username, " + 
			"	tel, " + 
			"	email, " + 
			"	balance, " + 
			"	equity, " + 
			"	lossEquity, " + 
			"	warningEquity, " + 
			"	pureDeposit, " + 
			"	totalWarnCount, " + 
			"   recentModifyTime, " +
			"	enterTime, " + 
			"	status " + 
			"FROM sti_warn.WarnListTmp A " +
			"WHERE NOT EXISTS ( SELECT 1 FROM sti_warn.WarnList WHERE mt4Login = A.mt4Login ) ";
	
	
	// 3시간이 경과했으나 처리하지 않 MT4Login 건 수정 
	public static String updateLstSqlNew = "UPDATE sti_warn.WarnList A " + 
			"SET  " + 
			"	balance = ( SELECT balance FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	equity = ( SELECT equity FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	lossEquity = ( SELECT lossEquity FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	warningEquity = ( SELECT warningEquity FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	pureDeposit = ( SELECT pureDeposit FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	totalWarnCount = ( SELECT totalWarnCount FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"   recentModifyTime = ( SELECT recentModifyTime FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) " +
			"WHERE STATUS = 'New' " +
			"AND enterTime <=  DATE_ADD( ?,INTERVAL -" + CONSTANTS.NEW_CHECK_HOURS + "  ) ";
	
	// 처리 완료 였던 건에 대한 처리 
	public static String updateLstSqlDone = "UPDATE sti_warn.WarnList A " + 
			"SET  " + 
			"	balance = ( SELECT balance FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	equity = ( SELECT equity FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	lossEquity = ( SELECT lossEquity FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	warningEquity = ( SELECT warningEquity FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	pureDeposit = ( SELECT pureDeposit FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"	totalWarnCount = ( SELECT totalWarnCount FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " + 
			"   recentModifyTime = ( SELECT recentModifyTime FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login) , " +
			"   enterTime = ( SELECT enterTime FROM sti_warn.WarnListTmp WHERE mt4Login = A.mt4Login), " +
			"   totalWarnCount = totalWarnCount + 1, " + 
			"   status = 'New' " +
			"WHERE STATUS = 'Done' " +
			"AND enterTime <=  DATE_ADD( ?,INTERVAL -" + CONSTANTS.DONE_CHECK_HOURS + "  ) ";

	// Trade data select
	public static String tradeDataSql = "SELECT " +
			" TICKET, " +
			" LOGIN, " +
			" SYMBOL, " +
			" DIGITS, " +
			" CMD, " +
			" VOLUME, " +
			" OPEN_TIME, " +
			" OPEN_PRICE, " +
			" SL, " +
			" TP, " +
			" CLOSE_TIME, " +
			" EXPIRATION, " +
			" REASON, " +
			" CONV_RATE1, " +
			" CONV_RATE2, " +
			" COMMISSION, " +
			" COMMISSION_AGENT, " +
			" SWAPS, " +
			" CLOSE_PRICE, " +
			" PROFIT, " +
			" TAXES, " +
			" COMMENT, " +
			" INTERNAL_ID, " +
			" MARGIN_RATE, " +
			" TIMESTAMP, " +
			" MAGIC, " +
			" GW_VOLUME, " +
			" GW_OPEN_PRICE, " +
			" GW_CLOSE_PRICE, " +
			" MODIFY_TIME " +
			" FROM report_live.MT4_TRADES " +
			" WHERE PROFIT < VOLUME * -10 " +
			" AND CMD in ( 0, 1) " +
			" AND close_time = '1970-01-01 00:00:00' ";
	
	
	//신규등록인 MT4 Account 는 3 시간에 한 번씩 Warning 을 계속 보내
	public static String checkSQLForPush = "SELECT " +
			"	broker_id, " + 
			"	mt4Login, " + 
			"	username, " + 
			"	tel, " + 
			"	email, " + 
			"	balance, " + 
			"	equity, " + 
			"	lossEquity, " + 
			"	warningEquity, " + 
			"	pureDeposit, " + 
			"	totalWarnCount, " + 
			"   recentModifyTime, " +
			"	enterTime, " + 
			"	status " + 
			"FROM sti_warn.WarnList A " +
			"WHERE status = 'New'  " + 
			"AND ( enterTime = ? or enterTime <=  DATE_ADD( ?,INTERVAL -" + CONSTANTS.NEW_CHECK_HOURS + " ) ) ";
	

	public static String deleteWarnTradesLstTmpSql = "DELETE FROM sti_warn.WarnTradesTmp WHERE broker_id = " + CONSTANTS.BROKER_ID;
    
	public static String insertWarnTradeLstTmpSql = "INSERT INTO sti_warn.WarnTradesTmp ( " +
    		" TICKET, " +
			" LOGIN, " +
			" SYMBOL, " +
			" DIGITS, " +
			" CMD, " +
			" VOLUME, " +
			" OPEN_TIME, " +
			" OPEN_PRICE, " +
			" SL, " +
			" TP, " +
			" CLOSE_TIME, " +
			" EXPIRATION, " +
			" REASON, " +
			" CONV_RATE1, " +
			" CONV_RATE2, " +
			" COMMISSION, " +
			" COMMISSION_AGENT, " +
			" SWAPS, " +
			" CLOSE_PRICE, " +
			" PROFIT, " +
			" TAXES, " +
			" COMMENT, " +
			" INTERNAL_ID, " +
			" MARGIN_RATE, " +
			" TIMESTAMP, " +
			" MAGIC, " +
			" GW_VOLUME, " +
			" GW_OPEN_PRICE, " +
			" GW_CLOSE_PRICE, " +
			" MODIFY_TIME, " +
			" STATUS ," +
			" enterTime," +
			" broker_id" +
			" ) VALUES ( " + 
			" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,   " +
			" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,   " +
			" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,   " +
			" 'New',  " +
			" ?, " + 
			" " + CONSTANTS.BROKER_ID + " " + 
			" ) ";
    
    
	public static String insertWarnTradeLstSql = "REPLACE INTO sti_warn.WarnTrades ( " +
    		" TICKET, " +
			" LOGIN, " +
			" SYMBOL, " +
			" DIGITS, " +
			" CMD, " +
			" VOLUME, " +
			" OPEN_TIME, " +
			" OPEN_PRICE, " +
			" SL, " +
			" TP, " +
			" CLOSE_TIME, " +
			" EXPIRATION, " +
			" REASON, " +
			" CONV_RATE1, " +
			" CONV_RATE2, " +
			" COMMISSION, " +
			" COMMISSION_AGENT, " +
			" SWAPS, " +
			" CLOSE_PRICE, " +
			" PROFIT, " +
			" TAXES, " +
			" COMMENT, " +
			" INTERNAL_ID, " +
			" MARGIN_RATE, " +
			" TIMESTAMP, " +
			" MAGIC, " +
			" GW_VOLUME, " +
			" GW_OPEN_PRICE, " +
			" GW_CLOSE_PRICE, " +
			" MODIFY_TIME, " +
			" enterTime," +
			" broker_id" +
			" ) " + 
			" SELECT " + 
    		" TICKET, " +
			" LOGIN, " +
			" SYMBOL, " +
			" DIGITS, " +
			" CMD, " +
			" VOLUME, " +
			" OPEN_TIME, " +
			" OPEN_PRICE, " +
			" SL, " +
			" TP, " +
			" CLOSE_TIME, " +
			" EXPIRATION, " +
			" REASON, " +
			" CONV_RATE1, " +
			" CONV_RATE2, " +
			" COMMISSION, " +
			" COMMISSION_AGENT, " +
			" SWAPS, " +
			" CLOSE_PRICE, " +
			" PROFIT, " +
			" TAXES, " +
			" COMMENT, " +
			" INTERNAL_ID, " +
			" MARGIN_RATE, " +
			" TIMESTAMP, " +
			" MAGIC, " +
			" GW_VOLUME, " +
			" GW_OPEN_PRICE, " +
			" GW_CLOSE_PRICE, " +
			" MODIFY_TIME, " +
			" enterTime," +
			" broker_id" +
			" FROM sti_warn.WarnTradesTmp ";
    			

	public static String selectOpenedTrades = "SELECT TICKET FROM sti_warn.WarnTrades WHERE CLOSE_TIME = '1970-01-01 00:00:00'  ";
	
	// Trade data closedTradeCheckSql
	public static String closedTradeCheckSql = "SELECT CLOSE_TIME " +
			" FROM report_live.MT4_TRADES " +
			" WHERE TICKET = ? AND CLOSE_TIME <> '1970-01-01 00:00:00' ";
	
	public static String deleteTradeSql = "DELETE FROM sti_warn.WarnTrades WHERE TICKET = ? ";
	

}
