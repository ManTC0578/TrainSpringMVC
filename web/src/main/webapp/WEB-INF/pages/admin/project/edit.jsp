<%@ page import="java.util.Date" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="admin.project.form.title"/></title>
    <meta name="heading" content="Project Management"/>
</head>
<div class="pathway">
    <c:choose>
        <c:when test="${not empty item.pojo.projectID}">
            Edit <fmt:message key="label.project"/>
        </c:when>
        <c:otherwise>
            Add <fmt:message key="label.project"/>
        </c:otherwise>
    </c:choose>
</div>
<c:url var="url" value="/admin/project/edit.html"/>
<c:url var="listURL" value="/admin/project/list.html"/>

<div id="content">
<%--@elvariable id="item" type=""--%>
<form:form commandName="item" action="${url}" method="post" id="itemForm">
    <div class="box_container">
        <div class="header">
            <fmt:message key="admin.project.form.heading"/>
        </div>

        <div class="form">
            <table width="100%" cellpadding="5" cellspacing="5" border="0">
                <tr>
                    <td><fmt:message key="label.projectName"/></td>
                    <td>
                        <form:input path="pojo.projectName" size="40"/>
                        <form:errors path="pojo.projectName" cssClass="validateError"/>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="Start Date"/></td>
                    <td>
                        <fmt:formatDate pattern="${date_format}" value="${item.pojo.startDate}" var="start" />
                        <input id="startDate" name="pojo.startDate" value="${start}" type="text" size="15" />
                        <form:errors path="pojo.startDate" cssClass="validateError"/>
                    </td>
                </tr>

                <tr>
                    <td><fmt:message key="End Date"/></td>
                    <td>
                            <%--<%! Date endDate = $("endDate").val(); %>--%>
                        <fmt:formatDate pattern="${date_format}" value="${item.pojo.endDate}" var="endDate"/>
                        <input id="endDate" name="pojo.endDate" value="${end}" type="text" size="15" />
                        <form:errors path="pojo.endDate" cssClass="validateError"/>
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
            </table>
        </div>
    </div>

    <br>

    <div class="box_container" >
        <display:table name="userList" cellspacing="0" cellpadding="0" requestURI="${url}"
                       partialList="false" sort="external" size="${fn:length(userList)}" defaultsort="4" uid="tableList" pagesize="${items.maxPageItems}" class="table bright_blue_body" export="false">
            <display:column headerClass="table_header" property="fullName" escapeXml="true" sortable="true" sortName="fullName" titleKey="Full Name" style="width: 30%"/>
            <display:column headerClass="table_header" escapeXml="false" sortable="true" sortName="email" titleKey="admin.user.form.email" style="width: 30%">
                <a href="${editURL}?pojo.userID=${tableList.userID}">${tableList.email}</a>
            </display:column>

            <display:column headerClass="table_header" sortName="status" escapeXml="true" sortable="true" titleKey="label.status" style="width:10%">
                <c:choose>
                    <c:when test="${tableList.status == Constants['USER_ACTIVE']}">
                        <fmt:message key="label.active"/>
                    </c:when>
                    <c:when test="${tableList.status == Constants['USER_INACTIVE']}">
                        <fmt:message key="label.inactive"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="label.disabled"/>
                    </c:otherwise>
                </c:choose>
            </display:column>

            <display:column headerClass="table_header" sortName="createdDate" escapeXml="true" sortable="true" titleKey="label.createddate" style="width:10%">
                <fmt:formatDate pattern="${date_format}" value="${tableList.createdDate }"/>
            </display:column>

            <display:column headerClass="table_header" sortName="modifiedDate" escapeXml="true" sortable="true" titleKey="label.modifieddate" style="width:10%">
                <fmt:formatDate pattern="${date_format}" value="${tableList.modifiedDate }"/>
            </display:column>

            <%--<display:column sortable="false"  headerClass="table_header" url="/admin/user/edit.html"--%>
            <%--titleKey="label.options" style="width: 10%">--%>
            <%--<a href="${editURL}?pojo.userID=${tableList.userID}"><img src="<c:url value='/images/icons/edit.png'/>"/></a>--%>
            <%--<a name="deleteLink" id="d_${tableList.userID}_a" href="<c:url value='/admin/user/list.html'><c:param name="checkList" value="${tableList.userID}"/><c:param name="crudaction" value="delete"/></c:url>"><img id="d_${tableList.userID}" src="<c:url value='/images/icons/no.png'/>"/></a>--%>

            <%--</display:column>--%>
            <display:setProperty name="paging.banner.item_name" value="user"/>
            <display:setProperty name="paging.banner.items_name" value="users"/>
            <display:setProperty name="paging.banner.placement" value="bottom"/>
            <display:setProperty name="paging.banner.no_items_found" value=""/>

        </display:table>
        <table>
            <tr>
                <td></td>
                <td>
                    <form:hidden path="crudaction" id="crudaction" value="insert-update"/>
                    <form:hidden path="pojo.projectID"/>
                    <input type="button" value="<fmt:message key="button.save"/>" onclick="$('#itemForm').submit();"/>
                    <input type="button" value="<fmt:message key="button.back"/>" onclick="document.location.href='${listURL}';"/>

                </td>
            </tr>
        </table>
    </div>
    <c:if test="${not empty messageResponse}">
        <div style="text-align: left; color: green;">
            <label>${messageResponse}</label>
        </div>
    </c:if>
    </div>
</form:form>
</div>

<script type="text/javascript">
    <c:if test="${not empty items.crudaction}">
    highlightTableRows("tableList");
    </c:if>
    $(function() {
        $("#startDate" ).datepicker({ showOn: "button", buttonImageOnly: true, buttonImage: '<c:url value="/images/iconCalendar.png"/>', dateFormat:'dd/mm/yy' });
        $("#endDate" ).datepicker({ showOn: "button", buttonImageOnly: true, buttonImage: '<c:url value="/images/iconCalendar.png"/>', dateFormat:'dd/mm/yy' });

        $("#hiddenDeleteConfirmLink").click(function() {
            $('#crudaction').val('delete');
            jConfirm('<fmt:message key="user.disabled.confirm.message"/>', '<fmt:message key="delete.confirm.title"/>', function(r) {
                if(r) {
                    document.forms['listForm'].submit();
                }
            });
            return false;
        });
        $('a[name="deleteLink"]').click(function(eventObj) {
            jConfirm('<fmt:message key="user.disabled.one.confirm.message"/>', '<fmt:message key="delete.confirm.title"/>', function(r) {
                if(r) {
                    location.href = $('#' + eventObj.target.id + '_a').attr('href');
                }
            });
            return false;
        });

    });
</script>