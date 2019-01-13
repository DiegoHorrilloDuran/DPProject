<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="administrator.send" /></p>

<display:table name="messagesRecipient" id="msg" requestURI="${requestURI}" 
	pagesize="5" class=showMessages>
	<display:column title="From:" property="message.sender"/>
	<display:column title="Date:" property="message.sendingTime"
	 format="{0,date,dd/MM/yyyy HH:mm}" />
</display:table>