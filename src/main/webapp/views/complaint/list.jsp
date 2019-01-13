<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<p><spring:message code="complaint.list" /></p>


<body>
<display:table name="complaints" id="cmp" requestURI="${requestURI}" 
	pagesize="5" class=listComplaints>
	<display:column title="Ticker:" property="complaint.referee"/>
	<display:column title="Date:" property="complaint.date"
	 format="{0,date,dd/MM/yyyy HH:mm}" />
	 <display:column title="Referee:" property="complaint.referee"/>
	
</display:table>
<display:table><display:column title="Reports" property="complaint.reports"/>
	
	 </display:table>
	 <display:table>
	 <display:column title="Attachments" property="complaint.attachments"/>
	 </display:table>
	 
	 <input type="submit" name="create" value="<spring:message code="complaint.create"/>"/> 
	 <input type="submit" name="edit" value="<spring:message code="complaint.edit"/>"/> 
	
</body>
