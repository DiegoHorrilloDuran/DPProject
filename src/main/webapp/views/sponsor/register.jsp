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

<p><spring:message code="sponsor.register" /></p>
<form:form action="sponsor/register.do" modelAttribute="sponsor">
	
	<form:label path="name">
		<spring:message code="sponsor.name"/>
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name"/>
	<br/>
	<form:label path="middleName">
		<spring:message code="sponsor.middleName"/>
	</form:label>
	<form:input path="middleName"/>
	<form:errors cssClass="error" path="middleName"/>
	<br/>
	<form:label path="surname">
		<spring:message code="sponsor.surname"/>
	</form:label>
	<form:input path="surname"/>
	<form:errors cssClass="error" path="surname"/>
	<br/>
	<form:label path="photo">
		<spring:message code="sponsor.photo"/>
	</form:label>
	<form:input path="photo"/>
	<form:errors cssClass="error" path="photo"/>
	<br/>
	<form:label path="email">
		<spring:message code="sponsor.email"/>
	</form:label>
	<form:input path="email"/>
	<form:errors cssClass="error" path="email"/>
	<br/>
	<form:label path="phoneNumber">
		<spring:message code="sponsor.phoneNumber"/>
	</form:label>
	<form:input path="phoneNumber"/>
	<form:errors cssClass="error" path="phoneNumber"/>
	<br/>
	<form:label path="address">
		<spring:message code="sponsor.address"/>
	</form:label>
	<form:input path="address"/>
	<form:errors cssClass="error" path="address"/>
	<br/>
	<imput type="sumbit" name="save" value="<spring:message code="sponsor.save" />"/>
</form:form>
