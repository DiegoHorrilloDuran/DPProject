<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<p><spring:message code="workPlan.list" /></p>


<body>
<display:table name="workPlans" id="wp" requestURI="${requestURI}" 
	pagesize="5" class=listWorkPlans>
	<display:column title="FixUpTask:" property="workPlan.fixUpTask"/>
	 
			<form:label path="fixUpTask.ticker">
			<spring:message code="fixUpTask.ticker" />
		</form:label>
		<form:input path="ticker" />
		<form:errors cssClass="error" path="ticker" />
		<br/>
		
	 </display:table>
	 	 
	 <input type="submit" name="create" value="<spring:message code="workPlan.create"/>"/> 
	 <input type="submit" name="edit" value="<spring:message code="workPlan.edit"/>"/> 
	 <input type="submit" name="delete" value="<spring:message code="workPlan.delete"/>"/> 
</body>
