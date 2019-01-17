<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table name="fixUpTasks" id="fixUpTask"
	requestURI="${requestURI}"  pagesize="5" class="displaytag">
	<display:column property="ticker" titleKey="fixUpTask.ticker" />
	<display:column property="description" titleKey="fixUpTask.description" />
	<display:column property="address" titleKey="fixUpTask.address" />
	<display:column property="category.name" titleKey="fixUpTask.category" />
	<display:column property="warranty.title" titleKey="fixUpTask.warranty" />
	<display:column property="maxPrice.amount" titleKey="fixUpTask.maxPrice" />
	<display:column property="publishingDate"
		titleKey="fixUpTask.publishingDate" format="{0,date,dd/MM/yyyy HH:mm}" />
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="fixUpTask/customer/edit.do?fixUpTaskId=${fixUpTask.id}"> <spring:message
					code="fixUpTask.edit" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
		<div>
			<a href="fixUpTask/customer/create.do"> <spring:message
					code="fixUpTask.create" />
			</a>
		</div>
	</security:authorize>
	<display:column>
	<security:authorize access="hasRole('ADMIN')">
	
	
		<jstl:if test="${fixUpTask.warranty==null }">
			<a href="warranty/administrator/create.do?id=${fixUpTask.id}">
			
			 <spring:message
					code="fixUpTask.createWarranty" />
			
			</a>
		</jstl:if>
		
	</security:authorize>
	</display:column>
</display:table>