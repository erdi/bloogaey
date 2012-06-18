def path = "/WEB-INF/pages/nonDynamic/${params.page}.gtpl"
if (context.getResource(path)) {
	request.path = path
	forward '/WEB-INF/pages/staticPage.gtpl'
} else {
	forward '/not-found'
}
