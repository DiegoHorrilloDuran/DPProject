<%--
 * action-1.jsp
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

<p><spring:message code="tutorial.update" /></p>

<form:form action="tutorial/update.do" modelAttribute="tutorial">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<form:label path="title">
		<spring:message code="tutorial.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br/>
	<form:label path="lastUpdated">
		<spring:message code="tutorial.lastUpdated"/>
	</form:label>
	<form:input path="lastUpdated"/>
	<form:errors cssClass="error" path="lastUpdated"/>
	<br/>
	<form:label path="summary">
		<spring:message code="tutorial.summary"/>
	</form:label>
	<form:input path="summary"/>
	<form:errors cssClass="error" path="summary"/>
	<br/>
	<form:label path="pictures">
		<spring:message code="tutorial.pictures"/>
	</form:label>
	<form:input path="pictures"/>
	<form:errors cssClass="error" path="pictures"/>
	<br/>
	<input type="submit" name="save" value="<spring:message code="tutorial.save" />"/>
	<input type="button" name="cancel" value="<spring:message code="tutorial.cancel" />" onclick="javascript:relativeRedir('tutorial/list.do);"/>
</form:form>