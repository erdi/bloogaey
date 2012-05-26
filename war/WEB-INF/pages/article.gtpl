<!DOCTYPE html>
<html itemscope itemtype="http://schema.org/Blog">

<head>
    <% def post = request.post %>

	<title>${post.title} -- Marcin Erdmann's Blog</title>
    <% include '/WEB-INF/includes/syntaxHighlighting.gtpl' %>
    <% include '/WEB-INF/includes/meta.gtpl' %>

    <meta itemprop="name" content="${post.title}">
    <meta itemprop="description" content="${post.content.replaceAll('<[^>]*>', ' ').replaceAll('&[^;]*;', ' ').take(200)}...">

	<script type="text/javascript">var switchTo5x=true;</script>
	<script type="text/javascript" src="http://w.sharethis.com/button/buttons.js"></script>
	<script type="text/javascript">stLight.options({publisher: "cbddf18f-20e1-4279-aa2e-a4b1017a2ec8"}); </script>
</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>
		<div class="post">

			<h1 class="post-title">${post.title}</h1>

			<div class="post-date">
				<% if (user && users.isUserLoggedIn() && users.isUserAdmin()) { %>
				<form action="/admin/posts/edit/${post.key.name}" method="get">
					<input type="image" src="/images/pencil.png" alt="Edit" align="right">
				</form>
				<% } %>
				Posted on ${post.created.format('dd MMMM, yyyy')} (${post.created.pretty()})
				<% if (post.modified != null && post.modified != post.created && (post.modified.time - post.created.time > 86400000)) { %>
				&mdash;
				Modified on ${post.modified?.format('dd MMMM, yyyy')} (${post.modified?.pretty()})
				<% } %>

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

		<% if (request.getAttribute('originalURI').contains('article')) { %>
		<div class="text-center">
			<span class='st_twitter_large' displayText='Tweet'></span>
			<span class='st_dzone_large' displayText='DZone'></span>
			<span class='st_reddit_large' displayText='Reddit'></span>
			<span class='st_digg_large' displayText='Digg'></span>
			<span class='st_delicious_large' displayText='Delicious'></span>
			<span class='st_google_reader_large' displayText='Google Reader'></span>
			<span class='st_email_large' displayText='Email'></span>
			<span class='st_googleplus_large' displayText='Google +'></span>
			<span class='st_facebook_large' displayText='Facebook'></span>
		</div>

		<div id="comments">

			<script>
				var idcomments_acct = '48420206fa5b90254fd87dbed40ad023';
				var idcomments_post_id = '${post.key.name}';
				var idcomments_post_url;
			</script>
			<span id="IDCommentsPostTitle" style="display:none"></span>
			<script type='text/javascript' src='http://www.intensedebate.com/js/genericCommentWrapperV2.js'></script>
		</div>
		<% } %>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</body>
</html>