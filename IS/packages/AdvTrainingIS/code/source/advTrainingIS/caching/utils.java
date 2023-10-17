package advTrainingIS.caching;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-02-28 18:52:40 CET
// -----( ON-HOST: sagbase

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigInteger;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void calculateFactorial (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateFactorial)>> ---
		// @sigtype java 3.5
		// [i] field:0:required factorialInput
		// [o] field:0:required factorialResult
		// This service calculates a factorial result
		// In mathematics, the factorial of a non-negative integer n, denoted by n!, is the product of all positive integers less than or equal to n. 
		// For example,
		// 		5! = 5  \times  4  \times  3  \times  2  \times  1 = 120.  \ 
		// pipeline
		
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	in = IDataUtil.getString( pipelineCursor, "factorialInput" );
		pipelineCursor.destroy();
		
		int nin = Integer.parseInt(in);
		BigInteger fak = BigInteger.ONE;
		for (int k = 2; k <= nin; k++) {
			fak = fak.multiply(new BigInteger(Integer.toString(k)));
		}
		
		calls++;
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "factorialResult", fak.toString() );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void constructArgumentArray (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(constructArgumentArray)>> ---
		// @sigtype java 3.5
		// [i] field:0:required n
		// [o] field:1:required arguments
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		int n = Integer.parseInt(IDataUtil.getString( pipelineCursor, "n" ));
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		String[]	arguments = new String[2*n];
		for (int i = 0; i < n; i++) {
			arguments[i] = Integer.toString(1+i);
		}
		for (int i = n; 1 <= i; i--) {
			arguments[n+n-i] = Integer.toString(i);
		}
		
		IDataUtil.put( pipelineCursor_1, "arguments", arguments );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void getAndWipeCalls (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getAndWipeCalls)>> ---
		// @sigtype java 3.5
		// [o] field:0:required calls
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "calls", Integer.toString(calls));
		pipelineCursor.destroy();
		
		calls = 0;
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	static int calls = 0;
		
	// --- <<IS-END-SHARED>> ---
}

