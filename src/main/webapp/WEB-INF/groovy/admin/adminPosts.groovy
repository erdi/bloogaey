
request.posts  = datastore.execute {
    from posts
    sort desc by created
    where draft == false
    and type == 'post'
}

request.drafts  = datastore.execute {
    from posts
    sort desc by created
    where draft == true
}

forward '/WEB-INF/pages/admin/adminPosts.gtpl'