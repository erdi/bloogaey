<div class="right" id="sidebar_outer">
	<div id="sidebar">
		<%
			def originalURI = request.getAttribute('originalURI')
			def isAdmin = user && users.isUserLoggedIn() && users.isUserAdmin()
			if (isAdmin) {
		%>
				<div class="box">
					<div class="box_title">Administration</div>
					<div class="box_content">
						<ul class="nice-list">
							<li><a href="/admin/posts/create">Create new content <img src="/images/add.png" alt="New content" align="right"></a></li>
							<li><a href="/admin/posts">Posts</a></li>
							<li><a href="/admin/categories">Categories</a></li>
							<li><a href="/admin/media">Media</a></li>
							<li><a href="/admin/clearCache">Clear cached content</a></li>
							<li><a href="${users.createLogoutURL("/")}">Logout</a></li>
						</ul>
					</div>
				</div>
		<%
    		}
		%>

		<div class="box">
			<div class="box_title">Categories</div>
			<div class="box_content">
				<ul class="nice-list">
					<% datastore.execute{ from categories sort asc by name }.each { category -> %>
						<li>
							<a href="/category/${category.name}" alt="${category.description}">${category.name}</a>
						</li>
					<% } %>
				</ul>
			</div>
		</div>

		<%
			if (!isAdmin) {
		%>

			<div class="box">
				<div class="box_title">About</div>
				<div class="box_content">
					<p>This is a blog of a Polish geek who currently lives in London and is interested in all Groovy related technologies.</p>
				</div>
			</div>

			<div class="box">
				<div class="box_title">Groovy Projects</div>
				<div class="box_content">
					<ul class="nice-list">
						<li><a href="http://groovy.codehaus.org">Groovy</a> dynamic language</li>
						<li><a href="http://grails.org">Grails</a> web application framework</li>
						<li><a href="http://gebish.org">Geb</a> groovy browser automation</li>
						<li><a href="http://gaelyk.appspot.com">Gaelyk</a> lightweight toolkit for GAE</li>
					</ul>
				</div>
			</div>
		<%
			}
		%>
	</div>
</div>