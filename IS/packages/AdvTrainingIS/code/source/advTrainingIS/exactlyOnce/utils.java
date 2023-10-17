package advTrainingIS.exactlyOnce;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-31 04:23:17 CEST
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

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void calculateDateDifference (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateDateDifference)>> ---
		// @sigtype java 3.5
		// [i] field:0:required startDateTime
		// [i] field:0:required endDateTime
		// [i] field:0:required startDateFormat
		// [i] field:0:required endDateFormat
		// [o] field:0:required dateDifferenceSec
		// [o] field:0:required dateDifferenceMin
		// [o] field:0:required dateDifferenceHr
		// [o] field:0:required dateDifferenceDay
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String startDateTime = "";
		String endDateTime = "";
		String startDateFormat = "";
		String endDateFormat = "";
		if (pipelineCursor.first("startDateTime"))
		{
		  startDateTime = (String) pipelineCursor.getValue();
		}
		if (pipelineCursor.first("endDateTime"))
		{
		  endDateTime = (String) pipelineCursor.getValue();
		}
		if (pipelineCursor.first("startDateFormat"))
		{
		  startDateFormat = (String) pipelineCursor.getValue();
		}
		if (pipelineCursor.first("endDateFormat"))
		{
		  endDateFormat = (String) pipelineCursor.getValue();
		}
		
		if (startDateTime.equals("") || endDateTime.equals(""))
		  throw new ServiceException("Dates cannot be null");
		if (startDateFormat.equals("") || endDateFormat.equals(""))
		  throw new ServiceException("Date formats cannot be null");
		
		pipelineCursor.destroy();
		
		try
		{
		  SimpleDateFormat sdf = new SimpleDateFormat(startDateFormat);
		  Date sdt = sdf.parse(startDateTime);
		  SimpleDateFormat edf = new SimpleDateFormat(endDateFormat);
		  Date edt = edf.parse(endDateTime);
		  long timediff = edt.getTime() - sdt.getTime();
		
		  // SimpleDateFormat ssdf = new SimpleDateFormat("HH:mm:ss");
		  // Calendar cal = Calendar.getInstance();
		  // cal.clear();
		  // cal.set(Calendar.SECOND, (int) timediff /1000);
		
		  // Date newDateTime = cal.getTime();
		  // String displayTime=null;
		
		  // if (cal.get(Calendar.DAY_OF_YEAR) > 1 )
		  // displayTime = ssdf.format(newDateTime) + " and " +
		  // (cal.get(Calendar.DAY_OF_YEAR)-1) + " Day(s)" ;
		  // else
		  // displayTime = ssdf.format(newDateTime);
		
		  String displayTimeSec = Long.toString(timediff / 1000);
		  String displayTimeMin = Long.toString(timediff / 60000);
		  String displayTimeHr = Long.toString(timediff / 3600000);
		  String displayTimeDay = Long.toString(timediff / 86400000);
		
		  // pipeline
		  IDataCursor pipelineCursor_1 = pipeline.getCursor();
		  pipelineCursor_1.last();
		  pipelineCursor_1.insertAfter("dateDifferenceSec", displayTimeSec);
		  pipelineCursor_1.insertAfter("dateDifferenceMin", displayTimeMin);
		  pipelineCursor_1.insertAfter("dateDifferenceHr", displayTimeHr);
		  pipelineCursor_1.insertAfter("dateDifferenceDay", displayTimeDay);
		  pipelineCursor_1.destroy();
		}
		catch (ParseException e)
		{
		  throw new ServiceException("Error parsing the date time: " + e);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void createErrorStr (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createErrorStr)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required ErrorStr
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		// Error
		IData	Error = IDataUtil.getIData( pipelineCursor, "Error" );
		if ( Error != null) {
		  IDataCursor ErrorCursor = Error.getCursor();
		  String	adapterType = IDataUtil.getString( ErrorCursor, "adapterType" );
		  String	errorCategory = IDataUtil.getString( ErrorCursor, "errorCategory" );
		  String	errorText = IDataUtil.getString( ErrorCursor, "errorText" );
		  Object	eventId = IDataUtil.get( ErrorCursor, "eventId" );
		  Long    longEventId = (Long) eventId;
		
		  IDataUtil.put( pipelineCursor, "ErrorStr", "DocID:  " + longEventId +
		                                             "  Type:  " + adapterType +
		                                             "  Category:  " + errorCategory +
		                                             "  ErrorTxt:  " + errorText );
		  ErrorCursor.destroy();
		}
		pipelineCursor.destroy();
		
		// --- <<IS-END>> ---

                
	}



	public static final void modNums (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(modNums)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required a
		// [i] field:0:required b
		// [o] field:0:required modulo
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	a = IDataUtil.getString( pipelineCursor, "a" );
			String	b = IDataUtil.getString( pipelineCursor, "b" );
		
		int modulo = Integer.parseInt(a) % Integer.parseInt(b);
		// pipeline
		
		IDataUtil.put( pipelineCursor, "modulo", "" + modulo );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void writeToFile (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writeToFile)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required inputString
		
		// get pipeline data
		IDataCursor pipelineCursor = pipeline.getCursor ();
		pipelineCursor.first ("inputString");
		String inputString = (String) pipelineCursor.getValue ();
		pipelineCursor.destroy ();
			
		try
		{
		   Date today = new Date ();
		   SimpleDateFormat formatter = new SimpleDateFormat ("MM-dd-yyyy");
		   String regFileName = "ErrorFile_" + formatter.format (today) + ".txt";
				
		   StringBuffer strBuffer = new StringBuffer ();
				
		   strBuffer.append (inputString + "\n");
				
		   byte [] bufWrite = strBuffer.toString ().getBytes ();
				
		   FileOutputStream regWrite = new FileOutputStream (regFileName, true);
		   regWrite.write (bufWrite);
		   regWrite.close ();
							
		}
		
		catch (IOException ie) 
		{
		   ie.printStackTrace ();
		}
		catch (Exception e) 
		{
		   e.printStackTrace ();
		}
		
		// --- <<IS-END>> ---

                
	}
}

