package advTrainingIS.exactlyOnce;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-03-06 19:55:37 CET
// -----( ON-HOST: sagbase

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
import java.util.*;
import java.text.*;
// --- <<IS-END-IMPORTS>> ---

public final class statements

{
	// ---( internal utility methods )---

	final static statements _instance = new statements();

	static statements _newInstance() { return new statements(); }

	static statements _cast(Object o) { return (statements)o; }

	// ---( server methods )---




	public static final void addTransaction (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(addTransaction)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] recref:0:required Trans advTrainingIS.exactlyOnce.docs:Trans
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// Trans
			IData	Trans = IDataUtil.getIData( pipelineCursor, "Trans" );
			if ( Trans != null)
			{
				IDataCursor TransCursor = Trans.getCursor();
					String	Account = IDataUtil.getString( TransCursor, "Account" );
					String	TransactionNum = IDataUtil.getString( TransCursor, "TransactionNum" );
					String	Amount = IDataUtil.getString( TransCursor, "Amount" );
					String	Type = IDataUtil.getString( TransCursor, "Type" );
				TransCursor.destroy();
		// AllTransactions
		IDataCursor AllTransactionsCursor = AllTransactions.getCursor();
		
		// i.Transaction
		IData[]	Transaction = IDataUtil.getIDataArray( AllTransactionsCursor, "Transaction" );
		// Add new Transaction to list
		  IData	TempTransaction = IDataFactory.create();
		  IDataCursor TempTransactionCursor = TempTransaction.getCursor();
		  IDataUtil.put( TempTransactionCursor, "Account", Account );
		  IDataUtil.put( TempTransactionCursor, "TransactionNum", TransactionNum );
		  IDataUtil.put( TempTransactionCursor, "Amount", Amount );
		  IDataUtil.put( TempTransactionCursor, "Type", Type );
		  TempTransactionCursor.destroy();
		  if ( Transaction != null) {
		    IData[] TransactionOut = new IData[Transaction.length + 1];
		    for ( int i = 0; i < Transaction.length; i++ ) {
		      TransactionOut[i] = Transaction[i];
		    }
		    TransactionOut[Transaction.length] = TempTransaction;
		    IDataUtil.put( AllTransactionsCursor, "Transaction", TransactionOut );
		    AllTransactionsCursor.destroy();
		  } else {
		    IData[] TransactionOut2 = new IData[1];
		    TransactionOut2[0] = TempTransaction;
		    IDataUtil.put( AllTransactionsCursor, "Transaction", TransactionOut2 );
		  }
		}
		pipelineCursor.destroy();
		
		
		
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void deleteAllTransactions (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(deleteAllTransactions)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		synchronized(AllTransactions) {
		  IDataCursor AllTransactionsCursor = AllTransactions.getCursor();
		  // i.Transaction
		  IData[]	Transaction = IDataUtil.getIDataArray( AllTransactionsCursor, "Transaction" );
		  if ( Transaction != null) {
		    AllTransactionsCursor.delete();
		  }
		  AllTransactionsCursor.destroy();
		}
		pipelineCursor.destroy();
		
		// --- <<IS-END>> ---

                
	}



	public static final void getAllTransactions (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getAllTransactions)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] recref:1:required Transactions advTrainingIS.exactlyOnce.docs:Trans
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		synchronized(AllTransactions) {
		  IDataCursor AllTransactionsCursor = AllTransactions.getCursor();
		  // i.Transaction
		  IData[]	Transaction = IDataUtil.getIDataArray( AllTransactionsCursor, "Transaction" );
		  if ( Transaction != null) {
		    IDataUtil.put( pipelineCursor, "Transactions", Transaction );
		  }
		  AllTransactionsCursor.destroy();
		}
		pipelineCursor.destroy();
		
		// --- <<IS-END>> ---

                
	}



	public static final void getByTransactionNum (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getByTransactionNum)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required TransactionNum
		// [o] recref:0:required Trans advTrainingIS.exactlyOnce.docs:Trans
		
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	TransactionNum = "0";
		try {
		  TransactionNum = IDataUtil.getString( pipelineCursor, "TransactionNum" );
		}catch (Exception e){
		  return;  // get out since no Transaction # given
		}
		IDataCursor AllTransactionsCursor = AllTransactions.getCursor();
		
		IData[]	Transactions = IDataUtil.getIDataArray( AllTransactionsCursor, "Transaction" );
		if ( Transactions != null) {
		  for ( int i = 0; i < Transactions.length; i++ ) {
		    IDataCursor TransactionsCursor = Transactions[i].getCursor();
		    String	TransactionNum_1 = IDataUtil.getString( TransactionsCursor, "TransactionNum" );
		    if (TransactionNum_1.compareTo(TransactionNum) == 0) {
		       IData	Trans = IDataFactory.create();
		       IDataCursor TransCursor = Trans.getCursor();
		       IDataUtil.put( TransCursor, "Account", IDataUtil.getString( TransactionsCursor, "Account" ));
		       IDataUtil.put( TransCursor, "TransactionNum", TransactionNum );
		       IDataUtil.put( TransCursor, "Amount", IDataUtil.getString( TransactionsCursor, "Amount" ) );
		       IDataUtil.put( TransCursor, "Type", IDataUtil.getString( TransactionsCursor, "Type" ) );
		       TransCursor.destroy();
		       IDataUtil.put( pipelineCursor, "Trans", Trans );						
		       TransactionsCursor.destroy();
		       break;
		     } // end if (compareTo)
		  } // end for
		} // end if ( Transactions != null)
		AllTransactionsCursor.destroy();
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getRunTimeDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getRunTimeDate)>> ---
		// @sigtype java 3.5
		// [o] object:0:required DateTime
		// [o] field:0:required DateTimeStr
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		Object DateTime = new Object();
		
		IDataUtil.put( pipelineCursor, "DateTime", GlobalDateTime );
		IDataUtil.put( pipelineCursor, "DateTimeStr", GlobalDateTimeStr );
		pipelineCursor.destroy();
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void setRunTimeDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(setRunTimeDate)>> ---
		// @sigtype java 3.5
		// [i] object:0:required DateTime
		// [i] field:0:required DateTimeStr
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			Object	DateTime = IDataUtil.get( pipelineCursor, "DateTime" );
			String	DateTimeStr = IDataUtil.getString( pipelineCursor, "DateTimeStr" );
			GlobalDateTime = (Date) DateTime;
			GlobalDateTimeStr = DateTimeStr;
		pipelineCursor.destroy();
		
		
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static IData AllTransactions = IDataFactory.create();
	public static Date GlobalDateTime = null;
	public static String GlobalDateTimeStr = null;
	// --- <<IS-END-SHARED>> ---
}

