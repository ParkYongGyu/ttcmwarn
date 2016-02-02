package com.stifx.ttcmstiwarndata;

public class SQLSCOPYTABLE {

	public static String sqlQueryMaxTimeForTTCM_TRADES = "SELECT MODIFY_TIME " +
			"FROM sti_warn.maxtime  " +
			"WHERE id = 1 "; 

	public static String sqlQueryMaxTimeForTTCM_USERS = "SELECT MODIFY_TIME " +
			"FROM sti_warn.maxtime  " +
			"WHERE id = 2 ";
	
	public static String sqlQueryReplicaMT4_TRADES = "SELECT " +
			  "TICKET, " + // ` int(11) NOT NULL,
			  "LOGIN, " + // ` int(11) NOT NULL,
			  "SYMBOL, " + // ` char(16) NOT NULL,
			  "DIGITS, " + // ` int(11) NOT NULL,
			  "CMD, " + // ` int(11) NOT NULL,
			  "VOLUME, " + // ` int(11) NOT NULL,
			  "OPEN_TIME, " + // ` datetime NOT NULL,
			  "OPEN_PRICE, " + // ` double NOT NULL,
			  "SL, " + // ` double NOT NULL,
			  "TP, " + // ` double NOT NULL,
			  "CLOSE_TIME, " + // ` datetime NOT NULL,
			  "EXPIRATION, " + // ` datetime NOT NULL,
			  "REASON, " + // ` int(11) NOT NULL DEFAULT '0',
			  "CONV_RATE1, " + // ` double NOT NULL,
			  "CONV_RATE2, " + // ` double NOT NULL,
			  "COMMISSION, " + // ` double NOT NULL,
			  "COMMISSION_AGENT, " + // ` double NOT NULL,
			  "SWAPS, " + // ` double NOT NULL,
			  "CLOSE_PRICE, " + // ` double NOT NULL,
			  "PROFIT, " + // ` double NOT NULL,
			  "TAXES, " + // ` double NOT NULL,
			  "COMMENT, " + // ` char(32) NOT NULL,
			  "INTERNAL_ID, " + // ` int(11) NOT NULL,
			  "MARGIN_RATE, " + // ` double NOT NULL,
			  "TIMESTAMP, " + // ` int(11) NOT NULL,
			  "MAGIC, " + // ` int(11) NOT NULL DEFAULT '0',
			  "GW_VOLUME, " + // ` int(11) NOT NULL DEFAULT '0',
			  "GW_OPEN_PRICE, " + // ` int(11) NOT NULL DEFAULT '0',
			  "GW_CLOSE_PRICE, " + // ` int(11) NOT NULL DEFAULT '0',
			  "MODIFY_TIME " + // ` datetime NOT NULL,
			"FROM report_live.MT4_TRADES  " +
			"WHERE MODIFY_TIME > ? " +
			"ORDER BY MODIFY_TIME ASC";
	
	public static String sqlQueryReplicaMT4_USERS = "SELECT " +
			"LOGIN, " +  //int(11) PK 
			"GROUP, " +  //char(16) 
			"ENABLE, " +  //int(11)
			"ENABLE_CHANGE_PASS, " +  //int(11)
			"ENABLE_READONLY, " +  // int(11)
			"ENABLE_OTP, " +  //int(11)
			"PASSWORD_PHONE, " +  // char(32)
			"NAME, " +  // char(128)
			"COUNTRY, " +  // char(32)
			"CITY, " +  // char(32)
			"STATE, " +  // char(32)
			"ZIPCODE, " +  // char(16)
			"ADDRESS, " +  // char(128)
			"LEAD_SOURCE, " +  // char(32)
			"PHONE, " +  // char(32)
			"EMAIL, " +  // char(48)
			"COMMENT, " +  // char(64)
			"ID, " +  // char(32)
			"STATUS, " +  // char(16)
			"REGDATE, " +  // datetime
			"LASTDATE, " +  // datetime
			"LEVERAGE, " +  // int(11)
			"AGENT_ACCOUNT, " +  // int(11)
			"TIMESTAMP, " +  // int(11)
			"BALANCE, " +  // double
			"PREVMONTHBALANCE, " +  // double
			"PREVBALANCE, " +  // double
			"CREDIT, " +  // double
			"INTERESTRATE, " +  // double
			"TAXES, " +  // double
			"SEND_REPORTS, " +  // int(11)
			"MQID, " +  //int(10) UN
			"USER_COLOR, " +  // int(11)
			"EQUITY, " +  // double
			"MARGIN, " +  // double
			"MARGIN_LEVEL, " +  // double
			"MARGIN_FREE, " +  // double
			"CURRENCY, " +  // char(16)
			"API_DATA, " +  // blob
			"MODIFY_TIME " +  //datetime
			"FROM report_live.MT4_USERS  " +
			"WHERE MODIFY_TIME > ? " +
			"ORDER BY MODIFY_TIME ASC";
	
	public static String insertSqlForMT4_TRADES = "REPLACE INTO sti_warn.TTCM_MT4_TRADES ( " +
			  "TICKET, " + // ` int(11) NOT NULL,
			  "LOGIN, " + // ` int(11) NOT NULL,
			  "SYMBOL, " + // ` char(16) NOT NULL,
			  "DIGITS, " + // ` int(11) NOT NULL,
			  "CMD, " + // ` int(11) NOT NULL,
			  "VOLUME, " + // ` int(11) NOT NULL,
			  "OPEN_TIME, " + // ` datetime NOT NULL,
			  "OPEN_PRICE, " + // ` double NOT NULL,
			  "SL, " + // ` double NOT NULL,
			  "TP, " + // ` double NOT NULL,
			  "CLOSE_TIME, " + // ` datetime NOT NULL,
			  "EXPIRATION, " + // ` datetime NOT NULL,
			  "REASON, " + // ` int(11) NOT NULL DEFAULT '0',
			  "CONV_RATE1, " + // ` double NOT NULL,
			  "CONV_RATE2, " + // ` double NOT NULL,
			  "COMMISSION, " + // ` double NOT NULL,
			  "COMMISSION_AGENT, " + // ` double NOT NULL,
			  "SWAPS, " + // ` double NOT NULL,
			  "CLOSE_PRICE, " + // ` double NOT NULL,
			  "PROFIT, " + // ` double NOT NULL,
			  "TAXES, " + // ` double NOT NULL,
			  "COMMENT, " + // ` char(32) NOT NULL,
			  "INTERNAL_ID, " + // ` int(11) NOT NULL,
			  "MARGIN_RATE, " + // ` double NOT NULL,
			  "TIMESTAMP, " + // ` int(11) NOT NULL,
			  "MAGIC, " + // ` int(11) NOT NULL DEFAULT '0',
			  "GW_VOLUME, " + // ` int(11) NOT NULL DEFAULT '0',
			  "GW_OPEN_PRICE, " + // ` int(11) NOT NULL DEFAULT '0',
			  "GW_CLOSE_PRICE, " + // ` int(11) NOT NULL DEFAULT '0',
			  "MODIFY_TIME " + // ` datetime NOT NULL,
			  ") VALUES ( " + // ` datetime NOT NULL,
			  " ?,?,?,?,?,?,?,?,?,?, " +  
			  " ?,?,?,?,?,?,?,?,?,?, " + 
			  " ?,?,?,?,?,?,?,?,?,? " + 
			  " ) ";
	
	
		public static String insertSqlForMT4_USERS = "REPLACE INTO sti_warn.TTCM_MT4_USERS ( " +
			"LOGIN, " +  //int(11) PK 
			"GROUP, " +  //char(16) 
			"ENABLE, " +  //int(11)
			"ENABLE_CHANGE_PASS, " +  //int(11)
			"ENABLE_READONLY, " +  // int(11)
			"ENABLE_OTP, " +  //int(11)
			"PASSWORD_PHONE, " +  // char(32)
			"NAME, " +  // char(128)
			"COUNTRY, " +  // char(32)
			"CITY, " +  // char(32)
			"STATE, " +  // char(32)
			"ZIPCODE, " +  // char(16)
			"ADDRESS, " +  // char(128)
			"LEAD_SOURCE, " +  // char(32)
			"PHONE, " +  // char(32)
			"EMAIL, " +  // char(48)
			"COMMENT, " +  // char(64)
			"ID, " +  // char(32)
			"STATUS, " +  // char(16)
			"REGDATE, " +  // datetime
			"LASTDATE, " +  // datetime
			"LEVERAGE, " +  // int(11)
			"AGENT_ACCOUNT, " +  // int(11)
			"TIMESTAMP, " +  // int(11)
			"BALANCE, " +  // double
			"PREVMONTHBALANCE, " +  // double
			"PREVBALANCE, " +  // double
			"CREDIT, " +  // double
			"INTERESTRATE, " +  // double
			"TAXES, " +  // double
			"SEND_REPORTS, " +  // int(11)
			"MQID, " +  //int(10) UN
			"USER_COLOR, " +  // int(11)
			"EQUITY, " +  // double
			"MARGIN, " +  // double
			"MARGIN_LEVEL, " +  // double
			"MARGIN_FREE, " +  // double
			"CURRENCY, " +  // char(16)
			"MODIFY_TIME " +  //datetime
			") VALUES ( " + // ` datetime NOT NULL,
			" ?,?,?,?,?,?,?,?,?,?, " +  
			" ?,?,?,?,?,?,?,?,?,?, " + 
			" ?,?,?,?,?,?,?,?,?,? " + 
			" ?,?,?,?,?,?,?,?,? " + 
			" ) ";	
	

		public static String sqlUpdateMaxTimeForTTCM_TRADES = "UPDATE sti_warn.maxtime SET MODIFY_TIME = ? WHERE id = 2 ";

		public static String sqlUpdateMaxTimeForTTCM_USERS = "UPDATE sti_warn.maxtime SET MODIFY_TIME = ? WHERE id = 1 ";
			
}
