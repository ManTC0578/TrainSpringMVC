<%@page trimDirectiveWhitespaces="true"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="login.title"/></title>
</head>
<div id="content">
    <div class="box_container">
         <div class="loginBox">
             <div style="margin:50px;">
                 <form name="loginForm" action="<c:url value="/j_security_check"/>" method="post">
                 <table width="400px" cellpadding="5" cellspacing="5" border="0">
                     <tr>
                         <td colspan="2">Please enter your username and password to log in the system </td>

                     </tr>
                     <tr>
                         <td>Username</td>
                         <td><input type="text" name="j_username"/></td>
                     </tr>
                     <tr>
                         <td>Password</td>
                         <td><input type="password" name="j_password"/></td>
                     </tr>
                     <tr>
                         <td></td>
                         <td>
                             <input type="submit" name="buttonLogin" value="Login"/>
                             <a href="<c:url value="#"/>">Forgot password?</a>
                         </td>
                     </tr>
                     <c:if test="${not empty param.error}">
                     <tr>
                         <td colspan="2" style="color:red;">
                             <c:choose>
                                 <c:when test="${param.error == 1}">
                                     Your username and password does not match. Please try again.
                                 </c:when>
                                 <c:when test="${param.error == 2}">
                                     Your session has been expired. Please re-login and continue your work.
                                 </c:when>
                             </c:choose>
                         </td>
                     </tr>
                     </c:if>
                 </table>
                 </form>
             </div>
         </div>
    </div>
</div>