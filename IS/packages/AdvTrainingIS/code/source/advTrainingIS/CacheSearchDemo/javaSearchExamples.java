package advTrainingIS.CacheSearchDemo;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-03-24 02:31:00 EDT
// -----( ON-HOST: DAETRAIN00006.eur.ad.sag

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import com.wm.lang.ns.NSName;
import com.softwareag.cache.search.Person;
import com.wm.app.b2b.client.cache.config.SearchResult;
// --- <<IS-END-IMPORTS>> ---

public final class javaSearchExamples

{
	// ---( internal utility methods )---

	final static javaSearchExamples _instance = new javaSearchExamples();

	static javaSearchExamples _newInstance() { return new javaSearchExamples(); }

	static javaSearchExamples _cast(Object o) { return (javaSearchExamples)o; }

	// ---( server methods )---




	public static final void doGlobalSearch (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(doGlobalSearch)>> ---
		// @sigtype java 3.5
		IData idataFill = IDataFactory.create();
		IDataMap fillMap = new IDataMap(idataFill);
		fillMap.put("cacheManagerName", "TestCacheManager");
		fillMap.put("cacheName", "CustomerCache");
		fillMap.put("includeKey", "true");
		fillMap.put("includeValue", "true");
		fillMap.put("criteria", IDataFactory.create());
		try {
			IData result = Service.doInvoke(NSName.create("pub.cache:search"), idataFill);
			Object[] object = (Object[]) IDataUtil.get(result.getCursor(), "result");
			
			System.out.println("///*******************RESULT*****************************///");
			for (int i = 0; i < object.length; i++) {
				Person value = (Person) ((SearchResult) object[i]).getValue();
				System.out.println(value);
			}
			System.out.println("///*******************RESULT*****************************///");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void searchForAllAgeAbove30 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(searchForAllAgeAbove30)>> ---
		// @sigtype java 3.5
		IData idataFill = IDataFactory.create();
		IDataMap fillMap = new IDataMap(idataFill);
		fillMap.put("cacheManagerName", "TestCacheManager");
		fillMap.put("cacheName", "CustomerCache");
		fillMap.put("includeKey", "true");
		fillMap.put("includeValue", "true");
		
		IData condition1 = IDataFactory.create();
		IDataCursor cursor1 = condition1.getCursor();
		IDataUtil.put(cursor1, "OP1", "age");
		IDataUtil.put(cursor1, "OPR", "gt");
		IDataUtil.put(cursor1, "OP2", 30);
		
			
		fillMap.put("criteria", condition1);
		try {
			
		
			
			IData result = Service.doInvoke(NSName.create("pub.cache:search"), idataFill);
			Object[] object = (Object[]) IDataUtil.get(result.getCursor(), "result");
			
						System.out.println("///*******************RESULT*****************************///");
			for (int i = 0; i < object.length; i++) {
				Person value = (Person) ((SearchResult) object[i]).getValue();
				
				System.out.println(value);
				
			}
			System.out.println("///*******************RESULT*****************************///");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void searchForAllFemaleCustomers (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(searchForAllFemaleCustomers)>> ---
		// @sigtype java 3.5
		IData idataFill = IDataFactory.create();
		IDataMap fillMap = new IDataMap(idataFill);
		fillMap.put("cacheManagerName", "TestCacheManager");
		fillMap.put("cacheName", "CustomerCache");
		fillMap.put("includeKey", "true");
		fillMap.put("includeValue", "true");
		
		IData condition1 = IDataFactory.create();
		IDataCursor cursor1 = condition1.getCursor();
		IDataUtil.put(cursor1, "OP1", "gender");
		IDataUtil.put(cursor1, "OPR", "eq");
		IDataUtil.put(cursor1, "OP2", Person.Gender.FEMALE);
			
		fillMap.put("criteria", condition1);
		try {
			IData result = Service.doInvoke(NSName.create("pub.cache:search"), idataFill);
			Object[] object = (Object[]) IDataUtil.get(result.getCursor(), "result");
			
			System.out.println("///*******************RESULT*****************************///");
			String localName;
			int localAge;
			Person.Gender localGender;
			for (int i = 0; i < object.length; i++) {
				Person value = (Person) ((SearchResult) object[i]).getValue();
				
				localName = value.getFirst_name();
				localAge  = value.getAge();
				localGender = value.getGender();
				
				//System.out.println(value);
				System.out.println("Name = " + localName + " Age = " + localAge + " Gender = " + localGender);
			}
			System.out.println("///*******************RESULT*****************************///");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void searchForAllFemalesAndAgeAbove30 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(searchForAllFemalesAndAgeAbove30)>> ---
		// @sigtype java 3.5
		IData idataFill = IDataFactory.create();
		IDataMap fillMap = new IDataMap(idataFill);
		fillMap.put("cacheManagerName", "TestCacheManager");
		fillMap.put("cacheName", "CustomerCache");
		fillMap.put("includeKey", "true");
		fillMap.put("includeValue", "true");
		
		IData condition1 = IDataFactory.create();
		IDataCursor cursor1 = condition1.getCursor();
		IDataUtil.put(cursor1, "OP1", "age");
		IDataUtil.put(cursor1, "OPR", "gt");
		IDataUtil.put(cursor1, "OP2", 30);
		
		IData condition2 = IDataFactory.create();
		IDataCursor cursor4 = condition2.getCursor();
		IDataUtil.put(cursor4, "OP1", "gender");
		IDataUtil.put(cursor4, "OPR", "eq");
		IDataUtil.put(cursor4, "OP2", Person.Gender.FEMALE);
		
		IData criteria = IDataFactory.create();
		IDataCursor cursor3 = criteria.getCursor();
		IDataUtil.put(cursor3, "OP1", condition1);
		IDataUtil.put(cursor3, "OPR", "AND");
		IDataUtil.put(cursor3, "OP2", condition2);
		
		fillMap.put("criteria", criteria);
		try {
			
		
			
			IData result = Service.doInvoke(NSName.create("pub.cache:search"), idataFill);
			Object[] object = (Object[]) IDataUtil.get(result.getCursor(), "result");
			
			String localName;
			int localAge;
			Person.Gender localGender;
			System.out.println("///*******************RESULT*****************************///");
			for (int i = 0; i < object.length; i++) {
				Person value = (Person) ((SearchResult) object[i]).getValue();
				
				localName = value.getFirst_name();
				localAge  = value.getAge();
				localGender = value.getGender();
				
				//System.out.println(value);
				System.out.println("Name = " + localName + " Age = " + localAge + " Gender = " + localGender);
			}
			System.out.println("///*******************RESULT*****************************///");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// --- <<IS-END>> ---

                
	}
}

