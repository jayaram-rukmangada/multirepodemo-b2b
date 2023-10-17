package advTrainingIS.bigMemory;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-02-28 18:51:04 CET
// -----( ON-HOST: sagbase

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Random;
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




	public static final void generateRandomData (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomData)>> ---
		// @sigtype java 3.5
		// [i] field:0:required bytes
		// [o] object:1:required data
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		String	bytes = IDataUtil.getString( pipelineCursor, "bytes" );
		int nBytes = Integer.parseInt(bytes);
		
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		byte[]  bdata = new byte[nBytes];
		Byte[]	Bdata = new Byte[nBytes];
		random.nextBytes(bdata);
		for (int i = 0; i < Bdata.length; i++) {
			Bdata[i] = bdata[i];
		}
		IDataUtil.put( pipelineCursor_1, "data", Bdata );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	static Random random = new Random();
		
	// --- <<IS-END-SHARED>> ---
}

