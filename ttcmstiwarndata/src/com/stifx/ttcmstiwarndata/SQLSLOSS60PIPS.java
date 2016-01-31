package com.stifx.ttcmstiwarndata;

public class SQLSLOSS60PIPS {

	// Trade data select
	public static String tradeDataSqlFor60Pips = "SELECT " +
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
			" WHERE CMD in ( 0, 1)  ";
	
	

	public static String insertWarnTradeLstTmpSqlFor60Pips = "INSERT INTO sti_warn.WarnTradesLoss60pips ( " +
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
			

			" PROFITLOSS50PIPS, " +
			" PROFITLOSS60PIPS, " +
			" PROFITLOSS70PIPS, " +
			" PROFITLOSS80PIPS, " +
			" PROFITLOSS90PIPS, " +
			" PROFITLOSS100PIPS, " +
			" PROFITLOSS110PIPS, " +
			" PROFITLOSS120PIPS, " +
			" PROFITLOSS130PIPS, " +
			" PROFITLOSS140PIPS, " +
			" PROFITLOSS150PIPS, " +
  
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
			" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,   " +
			" ?, 'New', ?, " + CONSTANTS.BROKER_ID + " " + 
			" ) ";	
	
}
