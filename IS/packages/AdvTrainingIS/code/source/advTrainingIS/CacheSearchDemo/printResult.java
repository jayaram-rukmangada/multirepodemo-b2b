package advTrainingIS.CacheSearchDemo;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-03-24 02:31:02 EDT
// -----( ON-HOST: DAETRAIN00006.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.client.cache.config.SearchResult;
import com.softwareag.cache.search.Person;
// --- <<IS-END-IMPORTS>> ---

public final class printResult

{
	// ---( internal utility methods )---

	final static printResult _instance = new printResult();

	static printResult _newInstance() { return new printResult(); }

	static printResult _cast(Object o) { return (printResult)o; }

	// ---( server methods )---




	public static final void getresult (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getresult)>> ---
		// @sigtype java 3.5
		// [i] object:1:required results
		Object[] object = (Object[]) IDataUtil.get(pipeline.getCursor(), "result");
		 
		System.out.println("///*******************RESULT-START*****************************///");
		
		if (null != object) {
			for (int i = 0; i < object.length; i++) {
				//IData value = (IData) ((SearchResult) object[i]).getValue();
				Person value = (Person) ((SearchResult) object[i]).getValue();
			
				System.out.println(value);
				
			}
		
			System.out.println("///*******************RESULT-END*****************************///");
		}
		// --- <<IS-END>> ---

                
	}
}

