package com.stifx.ttcmstiwarndata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

public class CopyTables {
	public static void main(String args[]) {
	
		Logger logger = Logger.getLogger(CopyTables.class);
		
		Connection connRelica = null;
		Connection connStibrokers = null;
	
		PreparedStatement pstmtReplica = null;
	    Statement stmtReplica = null; 
	    ResultSet rsReplica = null;// 		
	    Timestamp timestampForTTCM_USERS = null;
	    Timestamp timestampModifiedForTTCM_USERS = null;
	    Timestamp timestampForTTCM_TRADES = null;
	    Timestamp timestampModifiedForTTCM_TRADES = null;
	    
	    PreparedStatement pstmtStiBroker = null;
	    
	    int i=0, j = 0;
	    
	    try{ 

            Class.forName(CONSTANTS.driverName); 
            
            connRelica = DriverManager.getConnection(CONSTANTS.dbURLRelica, CONSTANTS.dbURLRelicaUser, CONSTANTS.dbURLRelicaPassword); 
            logger.info( DateUtil.getTimeStampString() + " connRelica 접속 완료  !!");
            
            connStibrokers = DriverManager.getConnection(CONSTANTS.dbURLStiBrokers, CONSTANTS.dbURLStiBrokersaUser, CONSTANTS.dbURLStiBrokersPassword); 
            logger.info( DateUtil.getTimeStampString() + " connStibrokers 접속 완료  !!");

            connStibrokers.setAutoCommit(false);

            while(true) {
            	
	            stmtReplica = connStibrokers.createStatement(); 
	            rsReplica = null;
	            rsReplica = stmtReplica.executeQuery(SQLSCOPYTABLE.sqlQueryMaxTimeForTTCM_USERS);
	            
	            if (rsReplica != null) {
	            	if(rsReplica.next()) {
	            		timestampForTTCM_USERS = rsReplica.getTimestamp(1);
	            	}
	            }
            	
            	if(rsReplica != null) 
            		rsReplica.close();
            	if(stmtReplica != null) 
            		stmtReplica.close();

            	logger.info(  DateUtil.getTimeStampString() + "  TTCM_USERS 데이타 입력 시작   !!  이전에 "  + timestampForTTCM_USERS + " 까지 입력됨!");
            	
	            rsReplica = null;
	            
	            pstmtReplica  = connRelica.prepareStatement(SQLSCOPYTABLE.sqlQueryReplicaMT4_USERS);
	            pstmtReplica.setTimestamp(1, timestampForTTCM_USERS);
	            rsReplica = pstmtReplica.executeQuery();
	            
	            if (rsReplica != null) {
	            	while(rsReplica.next()) {

	                    pstmtStiBroker = connStibrokers.prepareStatement(SQLSCOPYTABLE.insertSqlForMT4_USERS);
	                    pstmtStiBroker.setInt(1, rsReplica.getInt(1));
	                    pstmtStiBroker.setString(2, rsReplica.getString(2));
	                    pstmtStiBroker.setInt(3, rsReplica.getInt(3));
	                    pstmtStiBroker.setInt(4, rsReplica.getInt(4));
	                    pstmtStiBroker.setInt(5, rsReplica.getInt(5));
	                    pstmtStiBroker.setInt(6, rsReplica.getInt(6));
	                    pstmtStiBroker.setString(7, rsReplica.getString(7));
	                    pstmtStiBroker.setString(8, rsReplica.getString(8));
	                    pstmtStiBroker.setString(9, rsReplica.getString(9));
	                    pstmtStiBroker.setString(10, rsReplica.getString(10));
	                    pstmtStiBroker.setString(11, rsReplica.getString(11));
	                    pstmtStiBroker.setString(12, rsReplica.getString(12));
	                    pstmtStiBroker.setString(13, rsReplica.getString(13));
	                    pstmtStiBroker.setString(14, rsReplica.getString(14));
	                    pstmtStiBroker.setString(15, rsReplica.getString(15));
	                    pstmtStiBroker.setString(16, rsReplica.getString(16));
	                    pstmtStiBroker.setString(17, rsReplica.getString(17));
	                    pstmtStiBroker.setString(18, rsReplica.getString(18));
	                    pstmtStiBroker.setString(19, rsReplica.getString(19));
	                    pstmtStiBroker.setTimestamp(20, rsReplica.getTimestamp(20));
	                    pstmtStiBroker.setTimestamp(21, rsReplica.getTimestamp(21));
	                    pstmtStiBroker.setInt(22, rsReplica.getInt(22));
	                    pstmtStiBroker.setInt(23, rsReplica.getInt(23));
	                    pstmtStiBroker.setInt(24, rsReplica.getInt(24));
	                    pstmtStiBroker.setDouble(25, rsReplica.getDouble(25));
	                    pstmtStiBroker.setDouble(26, rsReplica.getDouble(26));
	                    pstmtStiBroker.setDouble(27, rsReplica.getDouble(27));
	                    pstmtStiBroker.setDouble(28, rsReplica.getDouble(28));
	                    pstmtStiBroker.setDouble(29, rsReplica.getDouble(29));
	                    pstmtStiBroker.setDouble(30, rsReplica.getDouble(30));
	                    pstmtStiBroker.setInt(31, rsReplica.getInt(31));
	                    pstmtStiBroker.setInt(32, rsReplica.getInt(32));
	                    pstmtStiBroker.setInt(33, rsReplica.getInt(33));
	                    pstmtStiBroker.setDouble(34, rsReplica.getDouble(34));
	                    pstmtStiBroker.setDouble(35, rsReplica.getDouble(35));
	                    pstmtStiBroker.setDouble(36, rsReplica.getDouble(36));
	                    pstmtStiBroker.setDouble(37, rsReplica.getDouble(37));	 
	                    pstmtStiBroker.setString(38, rsReplica.getString(38));                   
	                    timestampModifiedForTTCM_USERS = rsReplica.getTimestamp(40);
	                    pstmtStiBroker.setTimestamp(39, timestampModifiedForTTCM_USERS);
	                    
	                    pstmtStiBroker.executeUpdate();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker.close();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker = null;   
	                    i++;
	            	}
	            }
            	
            	if(rsReplica != null) 
            		rsReplica.close();
            	if(pstmtReplica != null) 
            		pstmtReplica.close();
            	
            	if ( i > 0 ) {
	                pstmtStiBroker = connStibrokers.prepareStatement(SQLSCOPYTABLE.sqlUpdateMaxTimeForTTCM_USERS);
	                pstmtStiBroker.setTimestamp(1, timestampModifiedForTTCM_USERS);
	                pstmtStiBroker.executeUpdate();
	                if ( pstmtStiBroker != null ) 
	                	pstmtStiBroker.close();
	                if ( pstmtStiBroker != null ) 
	                	pstmtStiBroker = null;             
		            connStibrokers.commit();
            	} 
        		logger.info( DateUtil.getTimeStampString() + " TTCM_USERS 데이타 입력 완료 (" + i + " 건 ) !!");
            	i = 0;
	            
	            
	            

	            stmtReplica = connStibrokers.createStatement(); 
	            rsReplica = null;
	            rsReplica = stmtReplica.executeQuery(SQLSCOPYTABLE.sqlQueryMaxTimeForTTCM_TRADES);
	            
	            if (rsReplica != null) {
	            	if(rsReplica.next()) {
	            		timestampForTTCM_TRADES = rsReplica.getTimestamp(1);
	            	}
	            }
            	
            	if(rsReplica != null) 
            		rsReplica.close();
            	if(stmtReplica != null) 
            		stmtReplica.close();

            	logger.info( DateUtil.getTimeStampString() + " TTCM_TRADES 데이타 입력 시작    !!  이전에 "  + timestampForTTCM_TRADES + " 까지 입력됨!");
            	
	            rsReplica = null;
	            pstmtReplica  = connRelica.prepareStatement(SQLSCOPYTABLE.sqlQueryReplicaMT4_TRADES);
	            pstmtReplica.setString(1, "2016-02-02 09:42:18");
	            //pstmtReplica.setTimestamp(1, timestampForTTCM_TRADES); // 
	            rsReplica = pstmtReplica.executeQuery();
	            
	            if (rsReplica != null) {
	            	while(rsReplica.next()) {

	                    pstmtStiBroker = connStibrokers.prepareStatement(SQLSCOPYTABLE.insertSqlForMT4_TRADES);
	                    
	                    
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
	                    timestampModifiedForTTCM_TRADES = rsReplica.getTimestamp(30);
	                    pstmtStiBroker.setTimestamp(30, timestampModifiedForTTCM_TRADES);
	                    
	                    pstmtStiBroker.executeUpdate();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker.close();
	                    if ( pstmtStiBroker != null ) 
	                    	pstmtStiBroker = null;             
	                    j++;
	            	}
	            }
            	
            	if(rsReplica != null) 
            		rsReplica.close();
            	if(pstmtReplica != null) 
            		pstmtReplica.close();
            	
            	
            	if ( j > 0 ) {
            		pstmtStiBroker = connStibrokers.prepareStatement(SQLSCOPYTABLE.sqlUpdateMaxTimeForTTCM_TRADES);
	                pstmtStiBroker.setTimestamp(1, timestampModifiedForTTCM_TRADES);
	                pstmtStiBroker.executeUpdate();
	                if ( pstmtStiBroker != null ) 
	                	pstmtStiBroker.close();
	                if ( pstmtStiBroker != null ) 
	                	pstmtStiBroker = null;             
		            connStibrokers.commit();
            	}
        		logger.info( DateUtil.getTimeStampString() + " TTCM_TRADES 데이타 입력 완료 (" + j + " 건 ) !!");
	            j = 0;
	            
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
}
