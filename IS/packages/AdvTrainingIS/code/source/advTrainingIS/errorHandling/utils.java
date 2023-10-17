package advTrainingIS.errorHandling;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-03-05 15:14:00 CET
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




	public static final void calcTotalCost (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calcTotalCost)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional orderLineItems
		// [i] - field:0:required unitCost
		// [i] - field:0:required desc
		// [i] - field:0:required supplierPartID
		// [i] - field:0:required quantity
		// [o] field:0:required TotalCost
		// [o] field:0:required Status
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// orderLineItems
		    double TotalCost = 0.0;
		    double LineItemCost = 0.0;
			IData[]	orderLineItems = IDataUtil.getIDataArray( pipelineCursor, "orderLineItems" );
		
				for ( int i = 0; i < orderLineItems.length; i++ )
				{
					IDataCursor orderLineItemsCursor = orderLineItems[i].getCursor();
					String	unitCost = IDataUtil.getString( orderLineItemsCursor, "unitCost" );
					String	quantity = IDataUtil.getString( orderLineItemsCursor, "quantity" );
		            Double  doubleUnitCost = new Double(unitCost);
		            Double  doubleQuantity = new Double(quantity);
		            LineItemCost = doubleUnitCost.doubleValue() * doubleQuantity.doubleValue();
		            TotalCost = LineItemCost + TotalCost;
					orderLineItemsCursor.destroy();
				}
		
		
		// pipeline
		IDataUtil.put( pipelineCursor, "TotalCost", ""+TotalCost );
		IDataUtil.put( pipelineCursor, "Status", "Good" );
		pipelineCursor.destroy();
		
		
		// --- <<IS-END>> ---

                
	}



	public static final void checkBuyerIdentity (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(checkBuyerIdentity)>> ---
		// @sigtype java 3.5
		// [i] field:0:required BuyerIdentity
		// [o] field:0:required BuyerIdentity
		// [o] field:0:required ErrorMsg
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	BuyerIdentity = IDataUtil.getString( pipelineCursor, "BuyerIdentity" );
			if (BuyerIdentity.equalsIgnoreCase("Fox")){
				IDataUtil.put( pipelineCursor, "BuyerIdentity", BuyerIdentity.toUpperCase() + " INDUSTRIES" );
			} else {
				IDataUtil.put( pipelineCursor, "ErrorMsg", "Invalid Buyer: " + BuyerIdentity );
			}
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}
}

