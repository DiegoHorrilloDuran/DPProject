<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<p><spring:message code="tutorial.list" /></p>


<body>
<display:table name="tutorials" id="tut" requestURI="${requestURI}" 
	pagesize="5" class=listTutorials>
	<display:column title="Title:" property="tutorial.title"/>
	<display:column title="Last Update:" property="tutorial.lastUpdated"
	 format="{0,date,dd/MM/yyyy HH:mm}" />
	 
</display:table>
<display:table><display:column title="Sections" property="tutorial.sections"/>
	
	 </display:table>
	 <display:table>
	 <display:column title="Pictures" property="tutorial.pictures"/>
	 </display:table>
	 
	 <input type="submit" name="create" value="<spring:message code="tutorial.create"/>"/> 
	 <input type="submit" name="edit" value="<spring:message code="tutorial.edit"/>"/> 
	 <input type="submit" name="delete" value="<spring:message code="tutorial.delete"/>"/> 
</body>
