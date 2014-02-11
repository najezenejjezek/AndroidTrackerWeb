
<%@ page import="cz.actis.cms.Article" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'article.label', default: 'Article')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-article" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'article.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="body" title="${message(code: 'article.body.label', default: 'Body')}" />
					
						<th><g:message code="article.category.label" default="Category" /></th>
					
						<g:sortableColumn property="datePublished" title="${message(code: 'article.datePublished.label', default: 'Date Published')}" />
					
						<g:sortableColumn property="dateArchived" title="${message(code: 'article.dateArchived.label', default: 'Date Archived')}" />
					
						<g:sortableColumn property="articleState" title="${message(code: 'article.articleState.label', default: 'Article State')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${articleInstanceList}" status="i" var="articleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${articleInstance.id}">${fieldValue(bean: articleInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: articleInstance, field: "body")}</td>
					
						<td>${fieldValue(bean: articleInstance, field: "category")}</td>
					
						<td><g:formatDate date="${articleInstance.datePublished}" /></td>
					
						<td><g:formatDate date="${articleInstance.dateArchived}" /></td>
					
						<td>${fieldValue(bean: articleInstance, field: "articleState")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${articleInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
