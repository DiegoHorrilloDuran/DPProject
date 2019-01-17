<%--
 * action-2.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- Ver si falta algún import en la parte de script -->

<!-- Actualizar -->

<form:form modelAttribute="phase" action="${requestURI}"
	id="formPhase">
	<security:authorize access="hasRole('handyWorker')">
		<form:hidden path="id" />
		<form:hidden path="version" />
	</security:authorize>
	<form:hidden path="handyWorker" />

	<security:authorize access="hasRole('handyWorker')">

		<b><spring:message code="phase.list.handyWorker" /></b>
			: ${workPlans.handyWorker.name} ${workPlans.handyWorker.surname}
			<br>

		<b> <spring:message code="phase.update.comments" />
		</b>
		<br>

		<!-- Al necesitar tratamiento, se pasará desde el controlador la lista de comentarios ya tratada , ver que hx darle a los p-->

		<jstl:forEach var="comment" items="${comentarios}">

			<jstl:out value="${comment}"></jstl:out>
			<br>
		</jstl:forEach>
	</security:authorize>

	<security:authorize access="hasRole('handyWorker')">
		<form:hidden path="status" />
		<form:label path="comments">
			<b><spring:message code="workPlans.update.comments" /></b>
		</form:label>
		<form:textarea path="comments" />
		<br>
	</security:authorize>

	<security:authorize access="hasRole('handyWorker')">
		<form:label path="status">
			<b><spring:message code="phase.update.status" /></b>
		</form:label>
		<form:select path="status" id="estados">
			<form:options items="${estados}" />
		</form:select>
		<form:errors cssClass="error" path="status" />
		<br>
			
		<div id="phaseFields">

			<p id="texto">
				<b><spring:message code="workPlans.update.phase" /></b>
			</p>

			<form:label path="phase.title">
				<spring:message code="workPlans.update.phase.title" />
			</form:label>
			<form:input path="phase.title" id="Title" />
			<form:errors cssClass="error" path="phase.title" />
			<br>

			<form:label path="phase.description">
				<spring:message code="workPlans.update.phase.description" />
			</form:label>
			<form:input path="phase.description" id="Description" />
			<form:errors cssClass="error" path="phase.description" />
			<br>
			
			<form:label path="phase.startDate">
				<spring:message code="workPlans.update.phase.startDate" />
			</form:label>
			<form:input path="phase.startDate" placeholder="dd/MM/yy"
				format="dd/MM/yy" id="startDate" />
			<form:errors cssClass="error" path="phase.startDate" />
			

			<form:label path="phase.endDate">
				<spring:message code="workPlans.update.phase.endDate" />
			</form:label>
			<form:input path="phase.endDate" placeholder="dd/MM/yyyy"
				format="dd/MM/yyyy" id="endDate" />
			<form:errors cssClass="error" path="phase.endDate" />
			<br>

		</div>
	</security:authorize>
	<input type="submit" name="save"
		value="<spring:message code="phase.update.save" />" />

	<!-- El botón de cancelar conectarlo con las fixUpTask -->
	<input type="button" name="cancel"
		value="<spring:message code="phase.update.cancel" />"
		<security:authorize access="hasRole('handyWorker')">onclick="javascript: relativeRedir('fixUpTask/handyWorker/list.do');"</security:authorize>
		 />

</form:form>