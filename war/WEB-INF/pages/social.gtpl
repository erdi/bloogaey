<!DOCTYPE html>
<html>

	<head>
		<title>Twitter -- Marcin Erdmann's Blog</title>
		<% include '/WEB-INF/includes/meta.gtpl' %>
	</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>

		<h1 class="page-heading">Twitter</h1>

		<p>You can follow me on <a href="http://twitter.com/marcinerdmann">Twitter</a> and see my latest tweets here:</p>

		<% request.items.each { date, entries -> %>
		<h2 class="archive-year">${date.format('yyyy / MM / dd')}</h2>
		<ul class="nice-list social">
		<% entries.each { entry -> %>
			<li>
				<img src="/images/icon-${entry.origin}.png">
				<a href="${entry.link}">${entry.title}</a>
			</li>
		<%} %>
		</ul>
		<% } %>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</body>
</html>