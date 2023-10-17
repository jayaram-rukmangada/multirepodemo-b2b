package advTrainingIS.pubSub;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-03-06 15:21:42 CET
// -----( ON-HOST: sagbase

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void InitRun (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(InitRun)>> ---
		// @sigtype java 3.5
		// [i] field:0:required IterationsToPerform
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	IterationsToPerform = IDataUtil.getString( pipelineCursor, "IterationsToPerform" );
		pipelineCursor.destroy();
			Count = (Long.parseLong(IterationsToPerform));
			InitTime = 0;
		// pipeline
			
		// --- <<IS-END>> ---

                
	}



	public static final void elapsedTime (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(elapsedTime)>> ---
		// @sigtype java 3.5
		// [i] field:0:required TimeElapsed
		// [o] field:0:required FinalTimeElapsed
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	TimeElapsed = IDataUtil.getString( pipelineCursor, "TimeElapsed" );
		pipelineCursor.destroy();
		if (InitTime == 0) {
				InitTime = Long.parseLong(TimeElapsed);
		}
		Count = Count -1;
		if (Count < 1) {
			IDataUtil.put( pipelineCursor, "FinalTimeElapsed", "" + (Long.parseLong(TimeElapsed) - InitTime) );
		}
		
		// --- <<IS-END>> ---

                
	}



	public static final void memoryHog (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(memoryHog)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required memoryToGrabPerObject
		// [i] field:0:required objectArraySize
		
		// get input on how many objects (array size) and how large an object (memoryperObject) from 
		// the pipeline
		
		
			IDataCursor pipelineCursor = pipeline.getCursor();
			Integer memoryInteger = new Integer(  IDataUtil.getString( pipelineCursor, "memoryToGrabPerObject" )  );
			Integer arraySizeInteger = new Integer(  IDataUtil.getString( pipelineCursor, "objectArraySize" )  );
		 
		// instantiate this arbitrarily sized object
		
			int arraySize = arraySizeInteger.intValue();
			Object[] memoryHolder = new Object[arraySize];
			int count = 0;
			for (; count < arraySize; count++)
			{
			  memoryHolder[count] = new byte[memoryInteger.intValue()];
			}
		
		//now copy this object
		
			Object[] copyOfMemHolder = new Object[arraySize];
			count = 0;
			for (; count < arraySize; count++)
			{
			  copyOfMemHolder[count] = memoryHolder[count];
			}
		
		pipelineCursor.destroy();
		
		// --- <<IS-END>> ---

                
	}



	public static final void thrasher (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(thrasher)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		if (requiredIterations < 0) {
		  String message = "You MUST Initialize requiredIterations so that this service\n" +
		                   "runs for 2000 to 3000 mSec. A good starting value might be 500000";
		  logger(message);
		  throw new ServiceException(new Exception(message));
		} else {
		  long starttime = System.currentTimeMillis();
		  double[] values = new double[10000];
		  long now;
		  long iterations = 0;
		  do {
		    now = System.currentTimeMillis();
		    for (int i=0; i<values.length; i++) {
		      values[i] = now * now;
		    }
		    if (++iterations % 10000 == 0) {
		      logger(Long.toString(iterations));
		    }
		  } while (iterations < requiredIterations);
		  logger("Completed in " + (now - starttime) + " ms");
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// 500000 might be a good starting point
	
	static long requiredIterations = -1; 
	static long InitTime = 0;
	static long Count = 0;
	private static void logger ( String message )
	
	{
	               Values input = new Values();
	               input.put("message", message);
	
	               try {
	                               Service.doInvoke("pub.flow", "debugLog", input);       
	               }
	               catch (Exception e)
	               {
	                               System.out.println(e.toString());
	               }
	
	}
	
	
		
	// --- <<IS-END-SHARED>> ---
}

