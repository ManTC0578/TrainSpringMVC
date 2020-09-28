<%@ include file="/common/taglibs.jsp"%>
<security:authorize ifAllGranted="ADMIN">
	<c:redirect url="/admin/dashboard.html"/>
</security:authorize>
<security:authorize ifAllGranted="USER">
	<c:redirect url="/user/dashboard.html"/>
</security:authorize>
<%--<security:authorize ifNotGranted="ADMIN,USER">--%>
    <%--<c:redirect url="/login.jsp"/>--%>
<%--</security:authorize>--%>

<security:authorize ifNotGranted="ADMIN,USER">
	<c:redirect url="/admin/user/list.html"/>
</security:authorize>