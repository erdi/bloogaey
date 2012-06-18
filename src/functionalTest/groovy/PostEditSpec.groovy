import geb.spock.GebSpec
import page.LoginPage
import groovyx.remote.client.RemoteControl
import groovyx.remote.transport.http.HttpTransport
import spock.lang.Shared
import com.google.appengine.api.datastore.Entity
import page.PostEditPage

class PostEditSpec extends GebSpec {

	@Shared def remote = new RemoteControl(new HttpTransport("http://localhost:8080/remote-control"))

	def setupSpec() {
		remote.useNullIfResultWasUnserializable = true
	}

	def cleanup() {
		remote.exec { datastore.iterate { select keys }.each { it.delete() } }
	}

	void 'some basics'() {
		given:
		remote.exec {
			new Entity('posts', 'test-post').with {
				markdown = 'Hello **World**!'
				content = '<p>Hello <strong>World</strong>!</p>'
				created = new Date()
				save()
			}
		}

		and:
		adminIsLoggedIn()

		when:
		to PostEditPage, 'test-post'

		then:
		postContents == 'Hello **World**!'
	}

	private void adminIsLoggedIn() {
		to LoginPage
		asAdmin = true
		login.click()
	}


}
