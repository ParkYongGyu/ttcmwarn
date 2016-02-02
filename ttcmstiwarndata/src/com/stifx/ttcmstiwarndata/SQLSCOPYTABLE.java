package com.stifx.ttcmstiwarndata;

public class SQLSCOPYTABLE {

	public static String sqlQueryMaxTimeForTTCM_TRADES = "SELECT id, name, MODIFY_TIME " +
			"FROM sti_warn.maxtime  " +
			"WHERE id = 1 "; 

	public static String sqlQueryMaxTimeForTTCM_USERS = "SELECT id, name, MODIFY_TIME " +
			"FROM sti_warn.maxtime  " +
			"WHERE id = 2 ";

	public static String sqlQueryReplica = "SELECT " +
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
			"FROM report_live.MT4_USERS  " +
			"WHERE MODIFY_TIME > ? ";
	
}
