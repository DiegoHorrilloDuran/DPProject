<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="customer.send" /></p>

<form:form action="customer/send.do" modelAttribute="message">
	
	<form:label path="body">
		<spring:message code="customer.body"/>
	</form:label>
	<form:input path="body"/>
	<form:errors cssClass="error" path="body"/>
	<br/>	
	<form:label path="recipient">
		<spring:message code="customer.recipient"/>
	</form:label>
	<form:input path="recipient"/>
	<form:errors cssClass="error" path="recipient"/>
	<br/>
	<imput type="sumbit" name="save" value="<spring:message code="customer.save" />"/>
</form:form>