<!DOCTYPE html>
<html>

	<%
		int page = params.page ? params.page.toInteger() : 0

		def originalURI = request.originalURI

		def titlePrefix = ''
		def origin = "archives"
		if (originalURI.contains('category')) {
			origin = "category/${params.category}"
			titlePrefix = "Category ${params.category} -- "
		} else {
			titlePrefix = "Archives -- "
		}
	%>

	<head>
		<title>${titlePrefix}Marcin Erdmann's Blog</title>
		<% include '/WEB-INF/includes/meta.gtpl' %>
		<meta name="robots" content="noindex,follow" />
	</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>

		<%
			if (origin.startsWith("archives")) {
				def date = [request.year, request.month, request.day].grep{ it }*.toString()*.padLeft(2, '0') %>
			<h1 class="page-heading">Archives ${date?'for':''} ${date.join('-')}</h1>
		<% } else if (origin.startsWith("category")) { %>
			<h1 class="page-heading">Category ${params.category}</h1>
		<% } %>
			<div class="archive-separator"></div>
		<%
			request.posts.each { year, posts ->
		%>
			<h2 class="archive-year">${year}</h2>
			<div class="archive-separator"></div>
		<%
				posts.each { post ->
					String day   = post.created.date.toString().padLeft(2, '0')
					String monthLetters = post.created.format('MMM').toUpperCase()
		%>

			<div class="archive-post">

				<div class="archive-post-date">
					<div class="archive-post-day">${day}</div>
					<div class="archive-post-month">${monthLetters}</div>
				</div>

				<div class="archive-post-title">
					<h3><a href="/article/${post.key.name}">${post.title}</a></h3>
					<div class="post-date">
						<% if (post.categories) { %>
							Posted in categories
						<% } else { %>
							No category
						<% } %>
						<% post.categories?.each { category -> %>
						<a href="/category/${category}">${category}</a>
						<% } %>
					</div>
				</div>

				<div class="clearer">&nbsp;</div>

			</div>

			<div class="archive-separator"></div>
		<%
				}
			}
		%>


		<%
			if (request.hasPrevious || page) {
		%>
			<div class="post-meta archive-pagination">
			<%
				if (request.hasPrevious) { %>
				<div class="left"><a href="/${origin}/p${page+1}">&#171; Previous page</a></div>
			<%
				}
				if (page) { %>
				<div class="right"><a href="/${origin}${(page-1) ? ('/p' + (page-1)) : ''}">Next page &#187;</a></div>
			<%
				}
			%>
				<div class="clearer">&nbsp;</div>
			</div>
		<% } %>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</html>