<!DOCTYPE html>
<html>

	<head>
		<% include '/WEB-INF/includes/meta.gtpl' %>
		<title>Not allowed -- Marcin Erdmann's Blog</title>
	</head>

<% include '/WEB-INF/includes/beforeContent.gtpl' %>

	<h1 class="page-heading">Access not allowed</h1>

	<p>You must be administrator to be able to administer this weblog.</p>
	<p>Please <a href="${users.createLoginURL('/admin/posts')}">sign-in</a> back with an administrator account.</p>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</html>