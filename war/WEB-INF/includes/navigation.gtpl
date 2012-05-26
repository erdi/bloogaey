<div id="navigation">
	<div id="nav1">
		<ul>
			<%
				def originalUri = request.getAttribute('originalURI')
				def styles = ['^/$', '(archives)|(category)', 'social', 'search', 'aboutThisSite', 'contact', 'bio', 'posts', 'categories', 'media'].collect {
					originalUri =~ it ? 'current_page_item' : ''
				}

				def links = [
						'/': 'Home',
						'/archives': 'Archives',
						'/social': 'Twitter',
						'/search': 'Search',
						'/page/aboutThisSite': 'Site',
						'/page/contact': 'Contact',
						'/page/bio': 'Bio',
						'/admin/posts': 'Posts',
						'/admin/categories': 'Categories',
						'/admin/media': 'Media'
				].entrySet().asList()

				links[0..6].eachWithIndex { entry, index ->
			%>
				<li class="${styles[index]}"><a href="${entry.key}">${entry.value}</a></li>
			<% }
				if (user && users.isUserLoggedIn() && users.isUserAdmin()) {
					links[7..9].eachWithIndex { entry, index ->
			%>
						<li class="${styles[index + 7]}"><a href="${entry.key}">${entry.value}</a></li>
			<%
					}
				}
			%>
		</ul>
		<a id="feed" href="http://feeds.feedburner.com/MarcinErdmannsBlogFeed">
			<img alt="Atom feed" src="/images/feed.png"/>
		</a>
		<div class="clearer">&nbsp;</div>
	</div>
</div>