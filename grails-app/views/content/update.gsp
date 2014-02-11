<%--
  Created by IntelliJ IDEA.
  User: radek.lunak
  Date: 31.1.14
  Time: 8:19
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<g:form controller="content" action="update" method="post">
    <table>
        <tr>
            <td><label for="categoryName" ><g:message code="article.category.name" default="Jmeno kategorie"/></label></td>
            <td><g:textField name="categoryName" value="${article.categoryName}" /></td>
        </tr>
        <tr>
            <td><label for="title"><g:message code="article.title" default="Titulek"></g:message></label></td>
            <td><g:textField name="title" value="${article.title}" /></td>
        </tr>
        <tr>
            <td><label for="body"><g:message code="article.body" default="Obsah"></g:message></label></td>
            <td><g:textField name="body" value="${article.title}" /></td>
        </tr>
        <tr>
            <td><g:actionSubmit name="update" title="${message(code: 'button.update',default: 'Aktualizovat')}" value="update"/></td>
        </tr>
    </table>
    <g:hiddenField name="id" value="${article?.id}" />
</g:form>

</body>
</html>