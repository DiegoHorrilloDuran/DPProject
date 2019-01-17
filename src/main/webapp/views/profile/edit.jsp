<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">
	<META HTTP-EQUIV="REFRESH" CONTENT="0;url=http://localhost:8080/Acme-Handy-Worker/administrator/edit.do">
</security:authorize>
<security:authorize access="hasRole('CUSTOMER')">
	<META HTTP-EQUIV="REFRESH" CONTENT="0;url=http://localhost:8080/Acme-Handy-Worker/customer/edit.do">
</security:authorize>
<security:authorize access="hasRole('HANDYWORKER')">
	<META HTTP-EQUIV="REFRESH" CONTENT="0;url=http://localhost:8080/Acme-Handy-Worker/handyWorker/edit.do">
</security:authorize>