<!DOCTYPE html>
<html>

	<head>
		<title>Marcin Erdmann's Blog</title>
		<% include '/WEB-INF/includes/syntaxHighlighting.gtpl' %>
		<% include '/WEB-INF/includes/meta.gtpl' %>
		<meta name="robots" content="noindex,follow"/>
	</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>

		<%
			if (params.staticPage) {
				include "/WEB-INF/pages/static/${params.staticPage}.gtpl"
			} else {
				if (!request.posts) {
		%>
					<h1>No more posts</h1>
		<%
				}
				request.posts.each { post ->
		%>
					<div class="post">
						<div class="post-title"><h1><a href="/article/${post.key.name}">${post.title}</a></h1></div>
						<div class="post-date">
						<% if (user && users.isUserLoggedIn() && users.isUserAdmin()) { %>
							<form action="/admin/posts/edit/${post.key.name}" method="get">
								<input type="image" src="/images/pencil.png" alt="Edit" align="right">
							</form>
						<% } %>
							Posted on ${post.created.format('dd MMMM, yyyy')} (${post.created.pretty()})
						</div>

						<div class="post-body">
							${post.content}
						</div>

						<% if (post.categories) { %>
							<div class="post-meta">
								In categories:
								<% post.categories.each { category -> %>
									<a href="/category/${category}">${category}</a>
								<% } %>
							</div>
						<% } %>

					</div>

			<%
				}

				int page = request.page
				if (page || request.hasPrevious) {
			%>
				<div class="post-meta archive-pagination">
					<%
						if (request.hasPrevious) {
					%>
						<div class="left"><a href="/p${page + 1}">&#171; Previous page</a></div>
					<%
						}
						if (page) {
					%>
						<div class="right"><a href="${(page - 1) ? ('/p' + (page - 1)) : '/'}">Next page &#187;</a></div>
					<%
						}
					%>
					<div class="clearer">&nbsp;</div>
				</div>
		<%
				}
			}
		%>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</html>