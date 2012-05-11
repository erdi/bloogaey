<!DOCTYPE html>
<html>

	<head>
		<title>Search Marcin Erdmann's Blog</title>
		<script src="http://www.google.com/jsapi" type="text/javascript"></script>
		<script type="text/javascript">
			google.load('search', '1', {language : 'en'});
			google.setOnLoadCallback(function() {
				var customSearchControl = new google.search.CustomSearchControl('013939896723962546743:_f8aom6tzae');
				customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);
				customSearchControl.draw('cse');
			}, true);
		</script>
		<link rel="stylesheet" href="http://www.google.com/cse/style/look/default.css" type="text/css" />
		<link rel="stylesheet" href="/css/google-search.css" type="text/css" />
		<% include '/WEB-INF/includes/meta.gtpl' %>
	</head>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>
		<h1 class="page-heading">Search this site</h1>
		<div id="cse" style="width: 100%;">Loading</div>
	<% include '/WEB-INF/includes/afterContent.gtpl' %>

</html>