<!DOCTYPE html>
<html>
	<head>
		<% include '/WEB-INF/includes/meta.gtpl' %>
		<title>An error occured -- Marcin Erdmann's Blog</title>
	</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>

		<h1 class="page-heading">An error occured</h1>

		<%
			if (request.getAttribute('code')) {
		%>
			<dt><strong>Error code:</strong></dt>
			<dd>${request.code}</dd>
		<%
			}
			if (request.getAttribute('ex')) {
		%>
			<dt><strong>Exception:</strong></dt>
			<dd>${request.getAttribute('ex')}</dd>
		<%
			}
		%>
			<dt><strong>Message:</strong></dt>
			<dd>${request.getAttribute('msg')}</dd>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</html>