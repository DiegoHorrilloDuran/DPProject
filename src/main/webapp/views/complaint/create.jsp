<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="complaint.creation" /></p>
<form:form action="complaint/creation.do" modelAttribute="complaint">
	
	<form:label path="ticker">
	
	</form:label>
	<form:input path="ticker"/>
	<form:errors cssClass="error" path="ticker"/>
	<br/>
	<form:label path="date">
		
	</form:label>
	<form:input path="date"/>
	<form:errors cssClass="error" path="date"/>
	<br/>
	<form:textarea path="description">
	</form:textarea>
	<form:input path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br/>
	<form:label path="referee">
		
	</form:label>
	<form:input path="referee"/>
	<form:errors cssClass="error" path="referee"/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="complaint.save" />"/>
	<input type="button" name="cancel" value="<spring:message code="complaint.cancel" />" onclick="javascript:relativeRedir('complaint/list.do);"/>
</form:form>
