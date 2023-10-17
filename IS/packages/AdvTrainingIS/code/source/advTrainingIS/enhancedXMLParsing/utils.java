package advTrainingIS.enhancedXMLParsing;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-02-28 18:56:01 CET
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




	public static final void getLastNode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLastNode)>> ---
		// @sigtype java 3.5
		// [o] object:0:required node
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "node", node );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void storeNode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(storeNode)>> ---
		// @sigtype java 3.5
		// [i] object:0:required node
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		node = IDataUtil.get( pipelineCursor, "node" );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	private static Object node = null;
		
	// --- <<IS-END-SHARED>> ---
}

