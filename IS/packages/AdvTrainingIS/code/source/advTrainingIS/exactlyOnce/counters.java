package advTrainingIS.exactlyOnce;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-31 04:23:12 CEST
// -----( ON-HOST: sagbase.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;
import java.util.*;
import java.text.*;
// --- <<IS-END-IMPORTS>> ---

public final class counters

{
	// ---( internal utility methods )---

	final static counters _instance = new counters();

	static counters _newInstance() { return new counters(); }

	static counters _cast(Object o) { return (counters)o; }

	// ---( server methods )---




	public static final void incrementCount (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(incrementCount)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required NewCount
		synchronized (Count) {
		 long newCount = Count.longValue() + 1;
		 Count = new Long(newCount);
		 IDataCursor pipelineCursor = pipeline.getCursor();
		 IDataUtil.put( pipelineCursor, "NewCount", newCount + "" );
		 pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void incrementPubCount (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(incrementPubCount)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required NewCount
		synchronized (PubCount) {
		 long newCount = PubCount.longValue() + 1;
		 PubCount = new Long(newCount);
		 IDataCursor pipelineCursor = pipeline.getCursor();
		 IDataUtil.put( pipelineCursor, "NewCount", newCount + "" );
		 pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void resetCounts (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resetCounts)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		synchronized (PubCount) {
		 long newCount = 0;
		 PubCount = new Long(newCount);
		}
		synchronized (Count) {
		 long newCount = 0;
		 Count = new Long(newCount);
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	static Long Count = new Long(0);
	static Long PubCount = new Long(0);
	// --- <<IS-END-SHARED>> ---
}

