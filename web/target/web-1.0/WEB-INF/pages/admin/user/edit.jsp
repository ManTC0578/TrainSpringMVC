<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="admin.user.form.title"/></title>
    <meta name="heading" content="User Management"/>
</head>
<div class="pathway">
   <c:choose>
       <c:when test="${not empty item.pojo.userID}">
           Edit <fmt:message key="label.user"/>
       </c:when>
       <c:otherwise>
           Add <fmt:message key="label.user"/>
       </c:otherwise>
   </c:choose>
</div>
<c:url var="url" value="/admin/user/edit.html"/>
<c:url var="listURL" value="/admin/user/list.html"/>

<div id="content">
    <form:form commandName="item" action="${url}" method="post" id="itemForm">
        <div class="box_container">
            <div class="header">
               <fmt:message key="admin.user.form.heading"/>
            </div>

            <div class="form">
                <table width="100%" cellpadding="5" cellspacing="5" border="0">
                    <tr>
                        <td><fmt:message key="admin.useradmin.form.fullname"/></td>
                        <td>
                            <form:input path="pojo.fullName" size="40"/>
                            <form:errors path="pojo.fullName" cssClass="validateError"/>
                        </td>
                    </tr>
					<tr>
                        <td><fmt:message key="admin.user.form.email"/></td>
                        <td>
                            <form:input path="pojo.email" size="40"/>
                            <form:errors path="pojo.email" cssClass="validateError"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="admin.user.form.password"/></td>
                        <td>
                            <form:password path="pojo.password" size="40"/>
                            <form:errors path="pojo.password" cssClass="validateError"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="label.status"/></td>
                        <td>
                            <form:radiobutton path="pojo.status" value="${Constants['USER_ACTIVE'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.active"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['USER_INACTIVE'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.inactive"/></label>
                            <form:radiobutton path="pojo.status" value="${Constants['USER_DISABLED'] }" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.disabled"/></label>
                            <form:errors path="pojo.status" cssClass="validateError"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <form:hidden path="crudaction" id="crudaction" value="insert-update"/>
                            <form:hidden path="pojo.userID"/>
                            <input type="button" value="<fmt:message key="button.save"/>" onclick="$('#itemForm').submit();"/>
                            <input type="button" value="<fmt:message key="button.back"/>" onclick="document.location.href='${listURL}';"/>

                        </td>
                    </tr>
                </table>
            </div>
            <c:if test="${not empty messageResponse}">
                <div style="text-align: left; color: red;">
                    <label>${messageResponse}</label>
                </div>
            </c:if>
        </div>

    </form:form>
</div>