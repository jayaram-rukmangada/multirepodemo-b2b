package advTrainingIS.security;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-31 04:23:25 CEST
// -----( ON-HOST: sagbase.eur.ad.sag

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




	public static final void stringCompare (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringCompare)>> ---
		// @sigtype java 3.5
		// [i] field:0:required str1
		// [i] field:0:required str2
		// [o] field:0:required compareVal
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	str1 = IDataUtil.getString( pipelineCursor, "str1" );
			String	str2 = IDataUtil.getString( pipelineCursor, "str2" );
		pipelineCursor.destroy();
		int compareVal = str1.compareTo(str2);
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "compareVal", compareVal+"" );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}
}

