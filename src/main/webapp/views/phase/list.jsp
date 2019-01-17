<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<p><spring:message code="phase.list" /></p>


<body>
<display:table name="phase" id="phase" requestURI="${requestURI}" 
	pagesize="5" class=listPhase>
	
	 <display:column title="Title" property="phase.title"/>
	 <display:column title="Description" property="phase.description"/>
	 <display:column title="Start Date" property="phase.startDate"/>
	 <display:column title="End Date" property="phase.endDate"/>
	 
	 </display:table>
	 
	 <input type="submit" name="create" value="<spring:message code="workPlan.create"/>"/> 
	 <input type="submit" name="edit" value="<spring:message code="workPlan.edit"/>"/> 
	 <input type="submit" name="delete" value="<spring:message code="workPlan.delete"/>"/> 
</body>
