<%@ page import="cz.actis.cms.Category" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="category.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="32" value="${categoryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'urlId', 'error')} required">
	<label for="urlId">
		<g:message code="category.urlId.label" default="Url Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="urlId" pattern="${categoryInstance.constraints.urlId.matches}" required="" value="${categoryInstance?.urlId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'isValid', 'error')} ">
	<label for="isValid">
		<g:message code="category.isValid.label" default="Is Valid" />
		
	</label>
	<g:checkBox name="isValid" value="${categoryInstance?.isValid}" />
</div>

