<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="fixUpTask/customer/edit.do"
	modelAttribute="fixUpTask">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="publishingDate" />
	<form:hidden path="complaints" />
	<form:hidden path="applications" />
	<form:hidden path="customer" />
	<form:hidden path="workplan" />

	<form:label path="description">
		<spring:message code="fixUpTask.description" />
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	<form:label path="address">
		<spring:message code="fixUpTask.address" />
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />
	<form:label path="maxPrice">
		<spring:message code="fixUpTask.maxPrice" />
	</form:label>
	<form:input path="maxPrice.amount" />
	<form:errors cssClass="error" path="maxPrice" />
	<br />
	<form:label path="carryOutDate">
		<spring:message code="fixUpTask.carryOutDate" />
	</form:label>
	<form:input path="carryOutDate" />
	<form:errors cssClass="error" path="carryOutDate" />
	<br />
	<form:label path="category">
		<spring:message code="fixUpTask.category" />
	</form:label>
	<form:select id="categories" path="category">
		<form:options items="${categories}" itemLabel="name"
			itemValue="id" />
		<form:option value="0" label="----"></form:option>
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
	<form:label path="warranty">
		<spring:message code="fixUpTask.warranty" />
	</form:label>
	<form:select id="warranties" path="warranty">
		<form:options items="${warranties}" itemLabel="title"
			itemValue="id" />
		<form:option value="0" label="----"></form:option>
	</form:select>
	<form:errors cssClass="error" path="warranty" />

	<input type="submit" name="save"
		value="<spring:message code="fixUpTask.save" />" />
	<input type="submit" name="delete"
		value="<spring:message code="fixUpTask.delete" />" />
	<input type="submit" name="cancel"
		value="<spring:message code="fixUpTask.cancel" />" />
</form:form>