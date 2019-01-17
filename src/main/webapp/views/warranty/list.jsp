<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="warranties" id="row" requestURI="warranty/administrator/list.do" pagesize="5" class="displaytag" >
	
	<display:column property="title" titleKey="warranty.title" sortable="true" />
	<display:column>
		<a href="warranty/administrator/show.do?id=${row.id}">
			<spring:message code="warranty.show" />
		</a>
	</display:column>
	<display:column>
		<jstl:if test="${!row.finalMode}" >
			<a href="warranty/administrator/edit.do?id=${row.id}">
				<spring:message code="warranty.edit" />
			</a>
		</jstl:if>
	</display:column>
	
</display:table>
<div><spring:message code="warranty.warning"></spring:message></div>
<input type="button" name="create"
		value="<spring:message code="warranty.create" />"
		onclick="javascript: relativeRedir('fixUpTask/customer/list.do');" />