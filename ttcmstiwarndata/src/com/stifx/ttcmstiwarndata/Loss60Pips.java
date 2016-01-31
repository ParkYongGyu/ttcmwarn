package com.stifx.ttcmstiwarndata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

public class Loss60Pips {

	public static void main(String args[]) {
		
		Logger logger = Logger.getLogger(AppMain.class);
		
		String strTimeStamp = null;
		
		Connection connRelica = null;
		Connection connStibrokers = null;

        Statement stmtReplica = null; 
        ResultSet rsReplica = null;// 		
        
        PreparedStatement pstmtStiBroker = null;
        
        try{ 
            

        	

            Class.forName(CONSTANTS.driverName); 
            
            connRelica = DriverManager.getConnection(CONSTANTS.dbURLRelica, "traderstrust", "pwdtraderstrust0113"); 
            logger.info( DateUtil.getTimeStampString() + " connRelica 접속 완료  !!");
            
            connStibrokers = DriverManager.getConnection(CONSTANTS.dbURLStiBrokers, "supertaimodbuser", "pwdreplication15!%"); 
            logger.info( DateUtil.getTimeStampString() + " connStibrokers 접속 완료  !!");

            connStibrokers.setAutoCommit(false);


            	
            	strTimeStamp = DateUtil.getTimeStampString();
            	long currLongTimeVal = DateUtil.getDateTimestamp();
            	Timestamp nowSqlDate = new Timestamp(currLongTimeVal);
            	int volume = 0; //rsReplica.getInt(6))
            	double profit = 0.0d;
            	double profit50 = 0.0d;
            	double profit60 = 0.0d;
            	double profit70 = 0.0d;
            	double profit80 = 0.0d;
            	double profit90 = 0.0d;
            	double profit100 = 0.0d;
            	double profit110 = 0.0d;
            	double profit120 = 0.0d;
            	double profit130 = 0.0d;
            	double profit140 = 0.0d;
            	double profit150 = 0.0d;
	            
	            stmtReplica = connRelica.createStatement(); 
	             
	            rsReplica = null;
	            rsReplica = stmtReplica.executeQuery(SQLSLOSS60PIPS.tradeDataSqlFor60Pips);
	            
	            if (rsReplica != null) {
	            	while(rsReplica.next()) {


	                    pstmtStiBroker = connStibrokers.prepareStatement(SQLSLOSS60PIPS.insertWarnTradeLstTmpSqlFor60Pips);
	                    
	                    profit = rsReplica.getDouble(20);
	                    volume = rsReplica.getInt(6);
	                   
	                    
	                    if ( profit < -0.0d ) {
	                    	
	                    	
	                    	profit50 = -1.0d * (double) volume * 5.0d;
	                    	profit60 = -1.0d * (double) volume * 6.0d;
	                    	profit70 = -1.0d * (double) volume * 7.0d;
	                    	profit80 = -1.0d * (double) volume * 8.0d;
	                    	profit90 = -1.0d * (double) volume * 9.0d;
	                    	profit100 = -1.0d * (double) volume * 10.0d;
	                    	profit110 = -1.0d * (double) volume * 11.0d;
	                    	profit120 = -1.0d * (double) volume * 12.0d;
	                    	profit130 = -1.0d * (double) volume * 13.0d;
	                    	profit140 = -1.0d * (double) volume * 14.0d;
	                    	profit150 = -1.0d * (double) volume * 15.0d;                    	
	                    	
	                    	
	                    	
	                    } else {

	                    	
	                    	
	                    	
	                    	
	                    	profit50 = profit;
	                    	profit60 = profit;
	                    	profit70 = profit;
	                    	profit80 = profit;
	                    	profit90 = profit;
	                    	profit100 = profit;
	                    	profit110 = profit;
	                    	profit120 = profit;
	                    	profit130 = profit;
	                    	profit140 = profit;
	                    	profit150 = profit;                    	
	                    	
	                    }
	                    
	                    pstmtStiBroker.setInt(1, rsReplica.getInt(1));
	                    pstmtStiBroker.setInt(2, rsReplica.getInt(2));
	                    pstmtStiBroker.setString(3, rsReplica.getString(3));
	                    pstmtStiBroker.setInt(4, rsReplica.getInt(4));
	                    pstmtStiBroker.setInt(5, rsReplica.getInt(5));
	                    pstmtStiBroker.setInt(6, volume);
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
	                    pstmtStiBroker.setDouble(20, profit);

	                    pstmtStiBroker.setDouble(21, profit50);
	                    pstmtStiBroker.setDouble(22, profit60);
	                    pstmtStiBroker.setDouble(23, profit70);
	                    pstmtStiBroker.setDouble(24, profit80);
	                    pstmtStiBroker.setDouble(25, profit90);
	                    pstmtStiBroker.setDouble(26, profit100);
	                    pstmtStiBroker.setDouble(27, profit110);
	                    pstmtStiBroker.setDouble(28, profit120);
	                    pstmtStiBroker.setDouble(29, profit130);
	                    pstmtStiBroker.setDouble(30, profit140);
	                    pstmtStiBroker.setDouble(31, profit150);	                    
	                    
	                    
	                    pstmtStiBroker.setDouble(32, rsReplica.getDouble(21));
	                    pstmtStiBroker.setString(33, rsReplica.getString(22));
	                    pstmtStiBroker.setInt(34, rsReplica.getInt(23));
	                    pstmtStiBroker.setDouble(35, rsReplica.getDouble(24));	                    
	                    pstmtStiBroker.setInt(36, rsReplica.getInt(25));
	                    pstmtStiBroker.setInt(37, rsReplica.getInt(26));
	                    pstmtStiBroker.setInt(38, rsReplica.getInt(27));
	                    pstmtStiBroker.setInt(39, rsReplica.getInt(28));
	                    pstmtStiBroker.setInt(40, rsReplica.getInt(29));
	                    pstmtStiBroker.setTimestamp(41, rsReplica.getTimestamp(30));
	                    pstmtStiBroker.setTimestamp(42, nowSqlDate);
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


	            
	            logger.info( DateUtil.getTimeStampString() + " MT4LOGIN 기준으로 없는 데이타 입력 완  !!");

	            connStibrokers.commit();

	            //checkSQLForPush


            
            
        } catch (SQLException se) { 
        	se.printStackTrace();
        
        	logger.error(se.getMessage(), se); 
            
        } catch (Exception e) {
        	e.printStackTrace();
        	
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
	
}
