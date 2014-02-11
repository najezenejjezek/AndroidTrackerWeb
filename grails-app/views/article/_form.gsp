<%@ page import="cz.actis.cms.Article" %>



<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="article.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" maxlength="128" required="" value="${articleInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'body', 'error')} required">
	<label for="body">
		<g:message code="article.body.label" default="Body" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="body" cols="40" rows="5" maxlength="16368" required="" value="${articleInstance?.body}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="article.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${cz.actis.cms.Category.list()}" optionKey="id" required="" value="${articleInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'datePublished', 'error')} ">
	<label for="datePublished">
		<g:message code="article.datePublished.label" default="Date Published" />
		
	</label>
	<g:datePicker name="datePublished" precision="day"  value="${articleInstance?.datePublished}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'dateArchived', 'error')} ">
	<label for="dateArchived">
		<g:message code="article.dateArchived.label" default="Date Archived" />
		
	</label>
	<g:datePicker name="dateArchived" precision="day"  value="${articleInstance?.dateArchived}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'articleState', 'error')} required">
	<label for="articleState">
		<g:message code="article.articleState.label" default="Article State" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="articleState" from="${cz.actis.cms.ArticleState?.values()}" keys="${cz.actis.cms.ArticleState.values()*.name()}" required="" value="${articleInstance?.articleState?.name()}"/>
</div>

