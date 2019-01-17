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

<p><spring:message code="user.account.creation" /></p>
<form:form action="userAccount/create.do" modelAttribute="useraccount">
	
	<form:label path="username">
	
		<spring:message code="userAccount.username" />
	</form:label>
	<form:input path="username"/>
	<form:errors cssClass="error" path="username"/>
	<br/>
	
	<form:label path="password">
	<spring:message code="userAccount.password" />
	</form:label>
	<form:input path="password"/>
	<form:errors cssClass="error" path="password"/>
	<br/>
	
	
	 
<form:label path="authorities">
		<spring:message code="userAccount.role" />
	</form:label>

	<form:select id="authorities" path="authorities">
		<form:option value="handyworker"  label="HANDYWORKER"></form:option>
		<form:option value="customer"  label="CUSTOMER"></form:option>
	</form:select>
	<form:errors cssClass="error" path="authorities" />
	<br />

	
	<input type="submit" name="save" value="<spring:message code="userAccount.save" />"/>
	<input type="button" name="cancel" value="<spring:message code="userAccount.cancel" />" onclick="javascript:relativeRedir('#');"/>
</form:form>
