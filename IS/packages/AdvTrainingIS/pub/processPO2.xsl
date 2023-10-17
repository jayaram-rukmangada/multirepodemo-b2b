<?xml version="1.0"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.1">
  <xsl:output indent="yes" />
  <xsl:param name="resultTime"/>
  <xsl:param name="Status"/>
  <xsl:template match="/">
 
<html>
  <body>

    <table class="tableView">
      <tr>
        <td class="heading" colspan="2">Status:  <xsl:value-of select="$Status"/></td>
      </tr>
      <tr>
        <td class="heading" colspan="2">resultTime: <xsl:value-of select="$resultTime"/></td>
     </tr>
     <tr>
        <td class="heading" colspan="2">PONum:  <xsl:value-of select="purchaseOrder/header/PONumber"/></td>
      </tr>

      <tr>
	<td class="oddcol">Description</td>
	<td class="oddcol">Quantity</td>
      </tr>
      <xsl:for-each select="purchaseOrder/details/orderLineItems">
	<tr>
	  <td class="oddrowdata-r">
	    <xsl:value-of select="desc"/>
	  </td>
	  <td class="oddrowdata-l">
	    <xsl:value-of select="quantity"/>
	  </td>
	</tr>
      </xsl:for-each>
    </table>
   </body>
</html>

    
  </xsl:template>
</xsl:stylesheet>