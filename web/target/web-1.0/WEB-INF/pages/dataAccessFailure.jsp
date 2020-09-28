<%@ include file="/common/taglibs.jsp" %>

<%@ page language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title>Data Access Error</title>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
</head>

<body id="error">
    <div id="page">
        <div class="pathway">
            <fmt:message key="errorPage.heading"/>
        </div>
        <div id="content" class="clearfix">
            <div id="main">
                <fmt:message key="errorPage.unexpectedMessage"></fmt:message>
                <%@ include file="/common/messages.jsp" %>

            </div>
        </div>
    </div>
</body>
</html>


<a href="<c:url value="/"/>" onclick="history.back();return false">&#171; Back</a>
