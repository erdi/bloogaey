
// Max number of articles per page
int pageSize = 5

// Page number
int page = params.page ? params.page.toInteger() : 0

// Retrieve 5 posts
def foundPosts = datastore.execute {
    from posts
    limit pageSize offset pageSize * page
    where created < new Date()
    and draft == false
    and type == 'post'
    sort desc by created
}

request.hasPrevious = foundPosts.size() == pageSize && datastore.execute {
    from posts
    limit 1
    where created < foundPosts.last().created
    and draft == false
    and type == 'post'
}
request.posts = foundPosts
request.page = page

forward '/WEB-INF/pages/home.gtpl'
