<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>	 	
			<head>
				<title><xsl:value-of select="view/header" />
				</title>
			</head>
			<body>				
				<xsl:apply-templates/>						
			</body>
		</html>
	</xsl:template>

	<xsl:template match="view">		
			<xsl:apply-templates select="body" />		
	</xsl:template>
	
	<xsl:template match="body">
			<form action="" method="">	
			<xsl:attribute name="action">
	          		<xsl:value-of select="form/action" />
	       	</xsl:attribute>
	       	<xsl:attribute name="method">
	          		<xsl:value-of select="form/method" />
	       	</xsl:attribute>
			<xsl:apply-templates select="form" />
			</form>
	</xsl:template>
	
	<xsl:template match="form">
			<xsl:apply-templates select="textView"/>		  
			<xsl:apply-templates select="buttonView"/>		
	</xsl:template>

	<xsl:template match="textView">		
			<label><xsl:value-of select="label" /></label>
			<input  name="" type="">
				<xsl:attribute name="name">
	          		<xsl:value-of select="name" />
	       		 </xsl:attribute>	
	       		<xsl:attribute name="tyep">
	          		<xsl:value-of select="type" />
	       		 </xsl:attribute>
			</input>
			<br/>		
	</xsl:template>

	<xsl:template match="buttonView">
		<input  type="" value="" >
	       	 <xsl:attribute name="name">
	          	<xsl:value-of select="name"/>
	       	 </xsl:attribute>
	       	 
	       	 <xsl:attribute name="type">
	          	<xsl:value-of select="type"/>
	       	 </xsl:attribute>
	       	 <xsl:attribute name="value">
	          	<xsl:value-of select="value"/>
	       	 </xsl:attribute>
	     </input>					
	</xsl:template>
	
</xsl:stylesheet>  