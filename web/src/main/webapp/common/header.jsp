<%@page trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.banvien.myplatform.web.security.SecurityUtils"%>
<%--<c:set var="userName" value="<%=SecurityUtils.getPrincipal().getEmail()%>"/>--%>
<%@ include file="/common/taglibs.jsp"%>
<div class="center">
    <div class="left">
        <img src="<c:url value="/images/logo.jpg"/>"/>
    </div>
</div>
<div class="left">
    <div class="top_navigation">
        <a href="<c:url value="/"/>">Home</a>
        | <a href="<c:url value="/admin/settings.html"/>">System Settings</a>
        | <a href="<c:url value="/profile.html"/>">My Profile</a>
        | <a href="<c:url value="/logout"/>">Logout</a>
    </div>
</div>
<div class="right">
    <div class="top_navigation">
        Welcome, ${userName }
    </div>
</div>
