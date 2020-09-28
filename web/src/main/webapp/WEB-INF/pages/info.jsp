<%@ include file="/common/taglibs.jsp"%>

<head>
    <title>Server Information</title>
    <meta name="menu" content="Server Info"/>
</head>

<div class="pathway">
   Server Information
</div>
<div id="content">
	<div class="middle" style="height: 540px">
		<br/>
		<br/>
		<br/>	
		Server Info   :&nbsp;   <%=getServletContext().getServerInfo()%>
		<br/>
		<br/>
		<br/>	
		Server Name   :&nbsp;   <%=request.getServerName()%>
		<br/>
		<br/>
		<br/>
		IP Address    :&nbsp; <%=request.getLocalAddr() %>
		<br/>
		<br/>
		<br/>
		BU		  :&nbsp; <fmt:message key="webapp.version"/>
		<br/>	
		
	</div>
</div>
