<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Handy Worker Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/register.do"><spring:message code="master.page.administrator.register" /></a></li>
					<li><a href="warranties/administrator/list.do"><spring:message code="master.page.administrator.warranties" /></a></li>					
					<li><a href="categories/administrator/list.do"><spring:message code="master.page.administrator.categories" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('HANDYWORKER')">
			<li><a class="fNiv"><spring:message	code="master.page.handyworker" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixUpTasks/handyWorker/list.do"><spring:message code="master.page.handyworker.fixUpTask" /></a></li>
					<li><a href="fixUpTaskFilter/handyworker/edit.do"><spring:message code="master.page.handyworker.filter" /></a></li>					
					<li><a href="applications/handyworker/list.do"><spring:message code="master.page.handyworker.application" /></a></li>
					<li><a href="workplans/list.do"><spring:message code="master.page.handyworker.workplan" /></a></li>
				</ul>
			</li>
		</security:authorize>		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixUpTask/customer/list.do"><spring:message code="master.page.customer.fixUpTask" /></a></li>
					<li><a href="applications/customer/list.do"><spring:message code="master.page.customer.applications"/></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="profile/register.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/edit.do"><spring:message code="master.page.profile.edit" /></a></li>
					<li><a href="message/send.do"><spring:message code="master.page.profile.message" /></a></li>
					<li><a href="profile/messageBox.do"><spring:message code="master.page.profile.messageBox" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

