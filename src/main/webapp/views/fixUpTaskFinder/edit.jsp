<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>    

	<form:form action="fixUpTaskFinder/worker/edit.do" modelAttribute="fixUpTaskFinder">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="searchResult" />
		
		<form:label path="tickerKeyWord">
			<spring:message code="fixUpTaskFinder.tickerKeyWord" />
		</form:label>
		<form:input path="tickerKeyWord" />
		<form:errors cssClass="error" path="tickerKeyWord" />
		<br/>
		
		<form:label path="category">
			<spring:message code="fixUpTaskFinder.category.name" />
		</form:label>
		<form:input path="category" />
		<form:errors cssClass="error" path="category" />
		<br/>
		
		<form:label path="warranty">
			<spring:message code="fixUpTaskFinder.warranty.title" />
		</form:label>
		<form:input path="warranty" />
		<form:errors cssClass="error" path="warranty" />
		<br/>
		
		<form:label path="maxPrice">
			<spring:message code="fixUpTaskFinder.maxPrice" />
		</form:label>
		<form:input path="maxPrice" />
		<form:errors cssClass="error" path="maxPrice" />
		<br/>
		
		<form:label path="minPrice">
			<spring:message code="fixUpTaskFinder.minPrice" />
		</form:label>
		<form:input path="minPrice" />
		<form:errors cssClass="error" path="minPrice" />
		<br/>
		
		<form:label path="minDate">
			<spring:message code="fixUpTaskFinder.minDate" />
		</form:label>
		<form:input path="minDate" />
		<form:errors cssClass="error" path="minDate" />
		<br/>
		
		<form:label path="maxDate">
			<spring:message code="fixUpTaskFinder.maxDate" />
		</form:label>
		<form:input path="maxDate" />
		<form:errors cssClass="error" path="maxDate" />
		<br/>
		
		
		<input type="submit" name="save"
		value="<spring:message code="fixUpTaskFinder.save" />" />&nbsp;
		
	</form:form>

