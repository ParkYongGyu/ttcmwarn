package com.stifx.ttcmstiwarndata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppMain {
	
	
	public static void main(String args[]) {
		
		Logger logger = LoggerFactory.getLogger(AppMain.class);
		
		String strTimeStamp = null;
		
		Connection connRelica = null;
		Connection connStibrokers = null;

		PreparedStatement pstmtReplica = null;
        Statement stmtReplica = null; 
        ResultSet rsReplica = null;// 		
        
        PreparedStatement pstmtStiBroker = null;
        Statement stmtStiBroker = null; 
        ResultSet rsStiBroker = null;// 		
        
        
        try{ 
            
/*
1. 데이타 베이스 정보 입니다.

* PORT : 3306
* DB Instance : replicatedttdb
* user : 
* pwd : 

2. 데이타 조작은 아래 서버에서 해야 합니다.

3306
DB Instance Identifier : stifxbrokers
Username : 
Password : 
*/
        	int i = 0;
        	int j = 0;
        	int ticketNum = 0;
        	

            Class.forName(CONSTANTS.driverName); 
            
            connRelica = DriverManager.getConnection(CONSTANTS.dbURLRelica, "traderstrust", "pwdtraderstrust0113"); 
            logger.info( DateUtil.getTimeStampString() + " connRelica 접속 완료  !!");
            
            connStibrokers = DriverManager.getConnection(CONSTANTS.dbURLStiBrokers, "supertaimodbuser", "pwdreplication15!%"); 
            logger.info( DateUtil.getTimeStampString() + " connStibrokers 접속 완료  !!");

            connStibrokers.setAutoCommit(false);

            while(true) {
            	
            	strTimeStamp = DateUtil.getTimeStampString();
            	long currLongTimeVal = DateUtil.getDateTimestamp();
            	Timestamp nowSqlDate = new Timestamp(currLongTimeVal);
            	
                logger.info( strTimeStamp + " " + ++j + " 시작 !!");
                logger.info( nowSqlDate + " " + j + "[SQL TIme] 시작 !!");
	            	
	            executePreparedStatement( connStibrokers, 
	            		pstmtStiBroker, 
	            		SQLS.deleteTmpSql );
	            
	            stmtReplica = connRelica.createStatement(); 
	             
	            rsReplica = null;
	            rsReplica = stmtReplica.executeQuery(SQLS.sqlQueryReplica);
	            
	            if (rsReplica != null) {
	            	while(rsReplica.next()) {
	            		
	                    pstmtStiBroker = connStibrokers.prepareStatement(SQLS.insertTmpSql);
	                    pstmtStiBroker.setInt(1, rsReplica.getInt(1));
	                    pstmtStiBroker.setString(2, rsReplica.getString(2));
	                    pstmtStiBroker.setDouble(3, rsReplica.getDouble(3));
	                    pstmtStiBroker.setDouble(4, rsReplica.getDouble(4));
	                    pstmtStiBroker.setDouble(5, rsReplica.getDouble(5));
	                    pstmtStiBroker.setDouble(6, rsReplica.getDouble(6));
	                    pstmtStiBroker.setDouble(7, rsReplica.getDouble(7));
	                    pstmtStiBroker.setTimestamp(8, rsReplica.getTimestamp(8));
	                    pstmtStiBroker.setTimestamp(9, nowSqlDate);
	                    pstmtStiBroker.executeUpdate();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker.close();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker = null;               
	            	}
	            }

	            if ( rsReplica != null )
					rsReplica.close();
	            if ( rsReplica != null )
					rsReplica = null;

	            if( stmtReplica != null )
	            	stmtReplica.close();
	            if( stmtReplica != null )
	            	stmtReplica = null;
	            
	            
	            
	            logger.info( DateUtil.getTimeStampString() + " " + j + " 회 TMP 데이타 입력 완  !!");
	            

	            
	            logger.info( DateUtil.getTimeStampString() + " MT4LOGIN 기준으로 없는 데이타 입력 시  !!");
	            
	            executePreparedStatement( connStibrokers, 
	            		pstmtStiBroker, 
	            		SQLS.insertLstSqlNew );
                
	            executePreparedStatement( connStibrokers, 
	            		pstmtStiBroker, 
	            		SQLS.updateLstSqlNew,
	            		nowSqlDate );  
                
	            executePreparedStatement( connStibrokers, 
	            		pstmtStiBroker, 
	            		SQLS.updateLstSqlDone,
	            		nowSqlDate);  
            	
	            executePreparedStatement( connStibrokers, 
	            		pstmtStiBroker, 
	            		SQLS.deleteWarnTradesLstTmpSql );  
                
	            stmtReplica = connRelica.createStatement(); 
	             
	            rsReplica = null;
	            rsReplica = stmtReplica.executeQuery(SQLS.tradeDataSql);
	            
	            if (rsReplica != null) {
	            	while(rsReplica.next()) {
	            		
                		//" TICKET, " +
            			//" LOGIN, " +
            			//" SYMBOL, " +
            			//" DIGITS, " +
            			//" CMD, " +
            			//" VOLUME, " +
            			//" OPEN_TIME, " +
            			//" OPEN_PRICE, " +
            			//" SL, " +
            			//" TP, " +
            			//" CLOSE_TIME, " +
            			//" EXPIRATION, " +
            			//" REASON, " +
            			//" CONV_RATE1, " +
            			//" CONV_RATE2, " +
            			//" COMMISSION, " +
            			//" COMMISSION_AGENT, " +
            			//" SWAPS, " +
            			//" CLOSE_PRICE, " +
            			//" PROFIT, " +
	                    //" TAXES, " +
            			//" COMMENT, " +
            			//" INTERNAL_ID, " +
            			//" MARGIN_RATE, " +
            			//" TIMESTAMP, " +
            			//" MAGIC, " +
            			//" GW_VOLUME, " +
            			//" GW_OPEN_PRICE, " +
            			//" GW_CLOSE_PRICE, " +
            			//" MODIFY_TIME, " +

	                    pstmtStiBroker = connStibrokers.prepareStatement(SQLS.insertWarnTradeLstTmpSql);
	                    pstmtStiBroker.setInt(1, rsReplica.getInt(1));
	                    pstmtStiBroker.setInt(2, rsReplica.getInt(2));
	                    pstmtStiBroker.setString(3, rsReplica.getString(3));
	                    pstmtStiBroker.setInt(4, rsReplica.getInt(4));
	                    pstmtStiBroker.setInt(5, rsReplica.getInt(5));
	                    pstmtStiBroker.setInt(6, rsReplica.getInt(6));
	                    pstmtStiBroker.setTimestamp(7, rsReplica.getTimestamp(7));
	                    pstmtStiBroker.setDouble(8, rsReplica.getDouble(8));
	                    pstmtStiBroker.setDouble(9, rsReplica.getDouble(9));
	                    pstmtStiBroker.setDouble(10, rsReplica.getDouble(10));
	                    pstmtStiBroker.setTimestamp(11, rsReplica.getTimestamp(11));
	                    pstmtStiBroker.setTimestamp(12, rsReplica.getTimestamp(12));
	                    pstmtStiBroker.setInt(13, rsReplica.getInt(13));
	                    pstmtStiBroker.setDouble(14, rsReplica.getDouble(14));
	                    pstmtStiBroker.setDouble(15, rsReplica.getDouble(15));
	                    pstmtStiBroker.setDouble(16, rsReplica.getDouble(16));
	                    pstmtStiBroker.setDouble(17, rsReplica.getDouble(17));
	                    pstmtStiBroker.setDouble(18, rsReplica.getDouble(18));
	                    pstmtStiBroker.setDouble(19, rsReplica.getDouble(19));
	                    pstmtStiBroker.setDouble(20, rsReplica.getDouble(20));
	                    pstmtStiBroker.setDouble(21, rsReplica.getDouble(21));
	                    pstmtStiBroker.setString(22, rsReplica.getString(22));
	                    pstmtStiBroker.setInt(23, rsReplica.getInt(23));
	                    pstmtStiBroker.setDouble(24, rsReplica.getDouble(24));	                    
	                    pstmtStiBroker.setInt(25, rsReplica.getInt(25));
	                    pstmtStiBroker.setInt(26, rsReplica.getInt(26));
	                    pstmtStiBroker.setInt(27, rsReplica.getInt(27));
	                    pstmtStiBroker.setInt(28, rsReplica.getInt(28));
	                    pstmtStiBroker.setInt(29, rsReplica.getInt(29));
	                    pstmtStiBroker.setTimestamp(30, rsReplica.getTimestamp(30));
	                    pstmtStiBroker.setTimestamp(31, nowSqlDate);
	                    pstmtStiBroker.executeUpdate();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker.close();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker = null;               
	            	}
	            }

	            if ( rsReplica != null )
					rsReplica.close();
	            if ( rsReplica != null )
					rsReplica = null;

	            if( stmtReplica != null )
	            	stmtReplica.close();
	            if( stmtReplica != null )
	            	stmtReplica = null;                

	            executePreparedStatement( connStibrokers, 
	            		pstmtStiBroker, 
	            		SQLS.insertWarnTradeLstSql );  

	            stmtStiBroker = connStibrokers.createStatement(); 
	             
	            rsStiBroker = null;
	            rsStiBroker = stmtStiBroker.executeQuery(SQLS.selectOpenedTrades);
	            
	            if (rsStiBroker != null) {
	            	while(rsStiBroker.next()) {

	            		ticketNum = rsStiBroker.getInt(1);
	            		
	   	             
	    	            pstmtReplica = connRelica.prepareStatement(SQLS.closedTradeCheckSql);
	    	            pstmtReplica.setInt(1, ticketNum);
	    	            rsReplica = pstmtReplica.executeQuery();
	    	            
	    	            if (rsReplica != null) {
	    	            	if(rsReplica.next()) {
	    	            		
	    	            		executePreparedStatement( connStibrokers, 
	    	    	            		pstmtStiBroker, 
	    	    	            		SQLS.deleteTradeSql,
	    	    	            		ticketNum
	    	    	            		);  
	    	            		
	    	            	}
	    	            }
	            		
	    	            if ( rsReplica != null )
	    					rsReplica.close();
	    	            if ( rsReplica != null )
	    					rsReplica = null;

	    	            if( pstmtReplica != null )
	    	            	pstmtReplica.close();
	    	            if( pstmtReplica != null )
	    	            	pstmtReplica = null;    	            		
	            		
	            	}
	            }
	            
	            if ( rsStiBroker != null )
	            	rsStiBroker.close();
	            if ( rsStiBroker != null )
					rsStiBroker = null;
	            
	            if( stmtStiBroker != null )
	            	stmtStiBroker.close();
	            if( stmtStiBroker != null )
	            	stmtStiBroker = null;        
	            
	            
	            logger.info( DateUtil.getTimeStampString() + " MT4LOGIN 기준으로 없는 데이타 입력 완  !!");

	            connStibrokers.commit();

	            //checkSQLForPush

	            Thread.sleep(CONSTANTS.SLEEPTIME);
            }

            
            
        } catch (SQLException e) { 
        
        	logger.error(e.getMessage(), e); 
            
        } catch (Exception e) {
        	
            logger.error("MySql 데이터베이스에 문제가 있슴니다.", e); 
            logger.error(e.getMessage(), e); 
        
        } finally {
            try {
				
				if ( connRelica != null ) 
					connRelica.close(); 
				
				if ( connStibrokers != null ) 
					connStibrokers.close();
				

	            logger.info("conn close");
            } catch (SQLException se) {
            	
            }
        }
	}
	

	private static void executePreparedStatement( Connection conn, 
			PreparedStatement pstmt, 
			String sql ) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	    if ( pstmt != null ) 
	    	pstmt.close();
	    if ( pstmt != null ) 
	    	pstmt = null;
	}

	private static void executePreparedStatement( Connection conn, 
			PreparedStatement pstmt, 
			String sql,
			Timestamp nowSqlDate ) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		pstmt.setTimestamp(1, nowSqlDate);
		pstmt.executeUpdate();
	    if ( pstmt != null ) 
	    	pstmt.close();
	    if ( pstmt != null ) 
	    	pstmt = null;
	}

	private static void executePreparedStatement( Connection conn, 
			PreparedStatement pstmt, 
			String sql,
			int TICKET ) throws SQLException {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, TICKET);
		pstmt.executeUpdate();
	    if ( pstmt != null ) 
	    	pstmt.close();
	    if ( pstmt != null ) 
	    	pstmt = null;
	}
}
