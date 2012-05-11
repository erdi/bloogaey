<!DOCTYPE html>
<html>

	<head>
		<title>Administration: Posts</title>
		<% include '/WEB-INF/includes/meta.gtpl' %>
		<script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="/js/jquery.sTabs.min.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				jQuery('#stabs').sTabs();
			});
		</script>
		<link rel="stylesheet" href="/css/stabs.css">
	</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>
		<h1 class="page-heading">Posts</h1>
		<ul id="stabs">
			<li>
				<a href="#Drafts">Drafts</a>
			</li>
			<li>
				<a href="#Posts">Posts</a>
			</li>
		</ul>

		<%
			['Drafts': request.drafts, 'Posts': request.posts].each { type, posts ->
		%>
		<div id="${type}">
		<ul class="nice-list">
			<% posts.each { post -> %>
			<li>
				<form action="/admin/posts/delete/${post.key.name}" method="post">
					<input type="image" src="/images/cross.png" alt="Delete it?" align="right">
				</form>
				<form action="/admin/posts/edit/${post.key.name}" method="post">
					<input type="image" src="/images/pencil.png" alt="Edit it" align="right">
				</form>
			<% if (post.type == 'page') { %>
				<img src="/images/page_white_text.png" alt="Page" align="left">&nbsp;
			<% } else if (post.type == 'post') { %>
				<img src="/images/newspaper.png" alt="Post" align="left">&nbsp;
			<% } %>
				<strong><a href="/${post.type=='post'?'article':'page'}/${post.key.name}">${post.title}</a></strong>
				<br>
				<img src="/images/transparent.png" width="20" height="1">
				${post.created.format('yyyy / MM / dd')}
				<% if (post.categories) { %> &mdash; ${post.categories?.join(', ') ?: 'none'} <% } %>
			</li>
			<% } %>
		</ul>
		</div>
		<% } %>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</html>