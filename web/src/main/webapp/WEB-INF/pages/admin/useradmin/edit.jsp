<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="admin.useradmin.form.title"/></title>
    <meta name="heading" content="User Admin Management"/>
</head>
<div class="pathway">
   <c:choose>
       <c:when test="${not empty item.pojo.userAdminID}">
           Edit <fmt:message key="admin.useradmin.label"/>
       </c:when>
       <c:otherwise>
           Add <fmt:message key="admin.useradmin.label"/>
       </c:otherwise>
   </c:choose>
</div>
<c:url var="url" value="/admin/useradmin/edit.html"/>
<c:url var="listURL" value="/admin/useradmin/list.html"/>

<div id="content">
    <form:form commandName="item" action="${url}" method="post" id="itemForm">
        <div class="box_container">
            <div class="header">
               <fmt:message key="admin.useradmin.form.heading"/>
            </div>

            <div class="form">
                <table width="100%" cellpadding="5" cellspacing="5" border="0">
                    <tr>
                        <td><fmt:message key="admin.useradmin.form.username"/></td>
                        <td>
                            <form:input path="pojo.userName" size="40"/>
                            <form:errors path="pojo.userName"  cssClass="validateError"/>
                        </td>
                    </tr>

                    <tr>
                        <td><fmt:message key="admin.useradmin.form.password"/></td>
                        <td>
                            <form:password path="pojo.password" size="40"/>
                            <form:errors path="pojo.password" cssClass="validateError"/>
                        </td>

                    </tr>
                    <tr>
                        <td><fmt:message key="admin.useradmin.form.fullname"/></td>
                        <td>
                            <form:input path="pojo.fullName" size="40"/>
                            <form:errors path="pojo.fullName" cssClass="validateError"/>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="admin.useradmin.form.email"/></td>
                        <td>
                            <form:input path="pojo.email" size="40"/>
                            <form:errors path="pojo.email" cssClass="validateError"/>
                        </td>

                    </tr>
                    <tr>
                        <td><fmt:message key="label.status"/></td>
                        <td>
                            <form:radiobutton path="pojo.status" value="1" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.active"/></label>
                            <form:radiobutton path="pojo.status" value="0" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.inactive"/></label>
                        </td>

                    </tr>
                    <tr>
                        <td><fmt:message key="admin.useradmin.form.role"/></td>
                        <td>
                            <form:select path="pojo.role">
                                <form:options items="${roles}"/>
                            </form:select>
                        </td>

                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <form:hidden path="crudaction" id="crudaction" value="insert-update"/>
                            <form:hidden path="pojo.userAdminID"/>
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