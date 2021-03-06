<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="msg.send" /></p>

<form:form action="message/send.do" modelAttribute="message">
	
	<form:label path="body">
		<spring:message code="msg.body"/>
	</form:label>
	<form:input path="body"/>
	<form:errors cssClass="error" path="body"/>
	<br/>	
	<form:label path="recipient">
		<spring:message code="msg.recipient"/>
	</form:label>
	<form:input path="recipient"/>
	<form:errors cssClass="error" path="recipient"/>
	<br/>
	<input type="submit" name="save" value="<spring:message code="msg.save" />"/>
</form:form>