package page

import geb.Page

class PostEditPage extends Page {
	static url = 'admin/posts/edit'

	static content = {
		postContents { $('textarea', name: 'content') }
	}
}
