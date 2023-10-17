package advTrainingIS.webServices.async;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-03-21 20:48:02 CET
// -----( ON-HOST: sagbase

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Date;
import java.text.*;
import com.wm.app.b2b.server.Session;
import com.wm.app.b2b.server.User;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void getUserName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getUserName)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:required $session
		// [o] field:0:required User
		 
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		  	// $session
			IData	TheSession = IDataUtil.getIData( pipelineCursor, "$session" );
			if ( TheSession != null)
			{
		           Session mySession = (Session) TheSession;
		           User  myUser = mySession.getUser();
		           String myUserName = myUser.getName();
		           IDataUtil.put( pipelineCursor, "User", myUserName );
			}
		pipelineCursor.destroy();
		
		
		
		
		// --- <<IS-END>> ---

                
	}
}

