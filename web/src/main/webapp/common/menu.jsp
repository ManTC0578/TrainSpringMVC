<%@page trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="navigation">
    <h2>System Settings</h2>
    <ul>
        <li><a id="user_menu" onclick="toggleMenu(this)" onmouseout="swapClass(this, '')" onmouseover="swapClass(this, 'selected')" class="" href="#"><fmt:message key="admin.menu.user"/></a>
            <div id="user_menu_sub" class="hidden">
                <ul>
                    <li><a class="first " href="<c:url value="/admin/user/list.html"/>"><fmt:message key="admin.menu.user"/></a></li>
                    <li><a class=" " href="<c:url value="/admin/useradmin/list.html"/>"><fmt:message key="admin.menu.useradmin"/></a></li>

                </ul>
            </div>
        </li>
        <li><a id="exercise_menu" onclick="toggleMenu(this)" onmouseout="swapClass(this, '')" onmouseover="swapClass(this, 'selected')" class="" href="#"><fmt:message key="Exercise"/></a>
            <div id="exercise_menu_sub" class="hidden">
                <ul>
                    <li><a class="first " href="<c:url value="/admin/project/list.html"/>"><fmt:message key="Project"/></a></li>

                </ul>
            </div>
        </li>
        <li><a id="general_data_menu" onclick="toggleMenu(this)" onmouseout="swapClass(this, '')" onmouseover="swapClass(this, 'selected')" class="" href="#"><fmt:message key="admin.menu.generaldata"/></a>
            <div id="general_data_menu_sub" class="hidden">
                <ul>
                    <li><a class="first " href="<c:url value="/admin/country/list.html"/>"><fmt:message key="admin.menu.country"/></a></li>
                    <li><a class=" " href="<c:url value="/admin/province/list.html"/>"><fmt:message key="admin.menu.province"/></a></li>
                </ul>
            </div>
        </li>
    </ul>
</div>
