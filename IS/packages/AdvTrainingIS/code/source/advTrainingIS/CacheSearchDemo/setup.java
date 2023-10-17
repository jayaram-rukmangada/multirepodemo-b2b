package advTrainingIS.CacheSearchDemo;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-03-24 02:31:04 EDT
// -----( ON-HOST: DAETRAIN00006.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.filechooser.FileSystemView;
import com.wm.lang.ns.NSName;
import com.softwareag.cache.search.Person;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class setup

{
	// ---( internal utility methods )---

	final static setup _instance = new setup();

	static setup _newInstance() { return new setup(); }

	static setup _cast(Object o) { return (setup)o; }

	// ---( server methods )---




	public static final void fillCache (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(fillCache)>> ---
		// @sigtype java 3.5
		// load sample customer data into cache 
		
		IData idataFill = IDataFactory.create();
		IDataMap fillMap = new IDataMap(idataFill);
		fillMap.put("cacheManagerName", "TestCacheManager");
		fillMap.put("cacheName", "CustomerCache"); 
		
		String customerID = "cust";
		int randomAge;
		
		try {
						
		for (int i = 0; i < 10; i++) {
			randomAge = (int )(Math.random() * 50 + 1);
			fillMap.put("key", customerID + i);
			fillMap.put("value", new Person("cust name " + i, randomAge, i % 2 == 0 ? Person.Gender.MALE : Person.Gender.FEMALE));
							
				Service.doInvoke(NSName.create("pub.cache:put"), idataFill);
				
				System.out.println(Service.doInvoke(NSName.create("pub.cache:get"), idataFill));
					
		}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		// --- <<IS-END>> ---

                
	}
}

