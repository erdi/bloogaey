package page

import geb.Page

class LoginPage extends Page{
	static url = '_ah/login'

	static content = {
		asAdmin { $('#isAdmin') }
		login { $('input', name: 'action', value: 'Log In') }
	}
}
