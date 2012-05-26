<!DOCTYPE html>
<html>
<% import com.google.appengine.api.datastore.Key %>

<head>
	<title>Administration: Edit Posts</title>
    <% include '/WEB-INF/includes/meta.gtpl' %>

    <link rel="stylesheet" type="text/css" href="/css/chosen.css">

    <script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="/js/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="/js/postEdit.js"></script>
</head>
	<%
		def keyName = params.id
		def post = keyName ? datastore.get('posts', keyName) : null
	%>

	<% include '/WEB-INF/includes/beforeContent.gtpl' %>
		<h1 class="page-heading">Edit post</h1>
		<fieldset>
			<form action="/admin/posts/save" method="post">
				<% if (post) { %>
				<input type="hidden" name="id" value="${keyName}">
				<% } %>
				<div class="form-row">
					<div class="form-property form-required">Title</div>
					<div class="form-value">
						<input type="text" name="title" id="titleInput" value="${post ? post.title : ''}"
							   class="text" style="width: 96%">
						<div class="error hidden" id="titleInputError">
							Title must not be empty or has already been used for another article
						</div>
					</div>

				<div class="clearer">&nbsp;</div>
				</div>
				<table style="width: 95%; margin: 0; padding: 0" cellpadding="0" cellspacing="0">
					<col style="width: 40%">
					<col style="width: 30%">
					<col style="width: 30%">
					<tr>
						<td>
							<div class="form-row">
								<div class="form-property form-required">Created <em>(GMT time)</em></div>
								<div class="form-value">
									<input type="text" name="created" id="createdInput" class="text" size="16"
										   value="${(post ? post.created : new Date()).format('yyyy/MM/dd HH:mm')}" >
									<div class="error hidden" id="dateInputError">Incorrect date<br>(yyyy/MM/dd HH:mm)</div>
								</div>
							</div>
						</td>
						<td>
							<div class="form-row">
								<div class="form-property form-required">Type</div>
								<div class="form-value">
									<select name="type" style="width: 150px; height: 25px">
										<option value="post" ${!post || post?.type == 'post' ? 'selected' : ''}>Post</option>
									</select>
								</div>
							</div>
						</td>
						<td>
							<div class="form-row">
								<div class="form-property form-required">Status</div>
								<div class="form-value">
									<select name="draft" style="width: 150px; height: 25px">
										<option value="draft" ${!post || post?.draft ? 'selected' : ''}>Draft</option>
										<option value="published" ${post && !post?.draft ? 'selected' : ''}>Published</option>
									</select>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<div class="form-row">
					<div class="form-property form-required" style="margin-bottom: 8px">Categories</div>
					<div class="form-value">
						<select name="categories" id="categories" multiple="multiple" class="chzn-select"
								style="width: 610px" title="Select one or more categories">
							<% datastore.execute { from categories sort asc by name }.each { category -> %>
							<option value="${category.name}" ${post && category.name in post.categories ? 'selected' : ''}>${category.name}</option>
							<% }%>
						</select>
					</div>
					<div class="clearer">&nbsp;</div>
				</div>
				<br>
				<div class="form-row">
					<div class="form-property form-required">Content</div>
					<div class="form-value">
						<textarea rows="30" cols="87" name="content">${ post ? post.markdown : ''}</textarea>
					</div>
					<div class="clearer">&nbsp;</div>
				</div>
				<br>
				<div class="form-row form-row-submit">
					<div class="form-value">
						<input type="submit" id="submitButton" disabled
							   class="button" value="Save &#187;" />
					</div>
					<div class="clearer">&nbsp;</div>
				</div>
			</form>
		</fieldset>

	<% include '/WEB-INF/includes/afterContent.gtpl' %>
</body>
</html>