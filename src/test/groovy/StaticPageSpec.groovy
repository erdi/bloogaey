import spock.lang.Unroll
import javax.servlet.ServletContext

class StaticPageSpec extends BaseSpec {
	def setup() {
		groovlet 'staticPage.groovy'
	}

	@Unroll("we are forwarded to '#forward' if the requested #pageExistsText")
	void 'request is forwarded to appropriate gtpl if the requested static page exists or not'() {
		given:
		staticPage.context = Mock(ServletContext)
		staticPage.context.getResource(_) >> pageResource

		when:
		staticPage.get()

		then:
		staticPage.forward == forward

		where:
		pageResource << [new URL('http', 'proxerd.pl', ''), null]
		forward << ['/WEB-INF/pages/staticPage.gtpl', '/not-found']
		pageExistsText = "page does${pageResource ? '' : ' not'} exist"
	}

	void 'page name parameter is used when checking if a given page exists'() {
		given:
		staticPage.context = Mock(ServletContext)
		staticPage.params.page = 'bio'

		when:
		staticPage.get()

		then:
		1 * staticPage.context.getResource({it =~ /bio.gtpl$/})
	}
}
