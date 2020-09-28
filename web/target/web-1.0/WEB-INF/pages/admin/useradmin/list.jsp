<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="admin.useradmin.form.title"/></title>
    <meta name="heading" content="User Management"/>
</head>
<div class="pathway">
  <fmt:message key="admin.useradmin.form.heading"/>
</div>

<c:url var="url" value="/admin/useradmin/list.html"/>
<c:url var="editURL" value="/admin/useradmin/edit.html"/>
<div id="content">
    <form:form commandName="items" action="${url}" method="post" id="listForm">
        <div class="box_container">
            <div class="header">
                <fmt:message key="admin.useradmin.form.heading"/>
            </div>
            <div class="form">
                <table width="100%" cellpadding="5" cellspacing="5" border="0">
                    <tr>
                        <td><fmt:message key="admin.useradmin.form.username"/></td>
                        <td><form:input path="pojo.userName" size="40"/></td>

                        <td><fmt:message key="admin.useradmin.form.fullname"/></td>
                        <td><form:input path="pojo.fullName" size="40"/></td>

                    </tr>

                    <tr>
                        <td><fmt:message key="admin.useradmin.form.email"/></td>
                        <td><form:input path="pojo.email" size="40"/></td>

                        <td><fmt:message key="label.status"/></td>
                        <td>
                            <form:radiobutton path="pojo.status" value="" cssClass="radioCls" checked="true"/>&nbsp;<label><fmt:message key="label.all"/></label>
                            <form:radiobutton path="pojo.status" value="1" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.active"/></label>
                            <form:radiobutton path="pojo.status" value="0" cssClass="radioCls"/>&nbsp;<label><fmt:message key="label.inactive"/></label>
                        </td>

                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="3">
                            <form:hidden path="crudaction" id="crudaction" value="search"/>
                            <input type="button" value="<fmt:message key="button.search"/>" onclick="$('#listForm').submit();"/>
                        </td>
                    </tr>
                </table>
            </div>

        </div>

        <br/>
         <div class="box_container" >

            <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${url}"
                partialList="true" sort="external" size="${items.totalItems}" defaultsort="2" uid="tableList" pagesize="${items.maxPageItems}" class="table bright_blue_body" export="false">
                <display:column headerClass="table_header" sortable="false" style="width: 3%" title="<input type=\"checkbox\" name=\"allCheck\" id=\"allCheck\" onclick=\"checkAll('listForm', 'checkList', this)\">">
                            <input type="checkbox" name="checkList" value="${tableList.userAdminID}" onclick="checkAllIfOne('listForm', 'checkList', this, 'allCheck')">
                </display:column>
                <display:column headerClass="table_header" property="userName" escapeXml="true" sortable="true" sortName="userName" titleKey="admin.useradmin.form.username" style="width: 15%"/>
                <display:column headerClass="table_header"  escapeXml="false" sortable="true" sortName="fullName" titleKey="admin.useradmin.form.fullname" style="width: 20%" >
                    <a href="${editURL}?pojo.userAdminID=${tableList.userAdminID}">${tableList.fullName}</a>
                </display:column>
                <display:column headerClass="table_header" property="email" escapeXml="true" sortable="true" sortName="email" titleKey="admin.useradmin.form.email" style="width: 30%"/>
                <display:column headerClass="table_header" property="role" escapeXml="true" sortable="true" sortName="role" titleKey="admin.useradmin.form.role" style="width: 10%"/>
                <display:column headerClass="table_header" sortName="status" escapeXml="true" sortable="true" titleKey="label.status" style="width:20%">
                    <c:choose>
                        <c:when test="${tableList.status == 1}">
                            <fmt:message key="label.active"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="label.inactive"/>
                        </c:otherwise>

                    </c:choose>
                </display:column>

                <display:column sortable="false"  headerClass="table_header" url="/admin/useradmin/edit.html"
                                            titleKey="label.options" style="width: 20%">
                    <a href="${editURL}?pojo.userAdminID=${tableList.userAdminID}"><img src="<c:url value='/images/icons/edit.png'/>"/></a>
                    <a name="deleteLink" id="d_${tableList.userAdminID}_a" href="<c:url value='/admin/useradmin/list.html'><c:param name="checkList" value="${tableList.userAdminID}"/><c:param name="crudaction" value="delete"/></c:url>"><img id="d_${tableList.userAdminID}" src="<c:url value='/images/icons/no.png'/>"/></a>

                </display:column>
                <display:setProperty name="paging.banner.item_name" value="user"/>
                <display:setProperty name="paging.banner.items_name" value="users"/>
                <display:setProperty name="paging.banner.placement" value="bottom"/>
                <display:setProperty name="paging.banner.no_items_found" value=""/>
            </display:table>
            <div class="clear"></div>
        </div>
        <div class="adminListButton">
			<c:if test="${not empty totalDeleted}">
				<div class="msg-response"><fmt:message key="database.items.deleted"><fmt:param value="${totalDeleted}"/></fmt:message></div>
				<br />
			</c:if>
			<c:if test="${not empty messageResponse}">
				<div class="msg-response">${messageResponse }</div>
				<br/>
			</c:if>
            <input type="button" value="<fmt:message key="button.add"/>" onclick="document.location.href='${editURL}';"/>

			<c:if test="${not empty items.totalItems && items.totalItems > 0}">
                <input type="button" value="<fmt:message key="button.delete"/>" onclick="confirmDeleteItems();"/>
			</c:if>
		</div>

    </form:form>
</div>

<script type="text/javascript">
	<c:if test="${not empty items.crudaction}">
    	highlightTableRows("tableList");
    </c:if>
    $(function() {
    	$("#hiddenDeleteConfirmLink").click(function() {
        	$('#crudaction').val('delete');
    		jConfirm('<fmt:message key="delete.confirm.message"/>', '<fmt:message key="delete.confirm.title"/>', function(r) {
    		   if(r) {
    			   document.forms['listForm'].submit();
    		   }
    		});
    		return false;
    	});
    	$('a[name="deleteLink"]').click(function(eventObj) {
    		jConfirm('<fmt:message key="delete.one.confirm.message"/>', '<fmt:message key="delete.confirm.title"/>', function(r) {
    		   if(r) {
    			   location.href = $('#' + eventObj.target.id + '_a').attr('href');
    		   }
    		});
			return false;
        });

    });
    function confirmDeleteItems(){
    	var fb = checkIsOneItemSelected('listForm', 'checkList');
		if(fb) {
			$("#hiddenDeleteConfirmLink").trigger('click');
		}else {
			$("#hidenDeleteWarningLink").trigger('click');
		}
    }

</script>
