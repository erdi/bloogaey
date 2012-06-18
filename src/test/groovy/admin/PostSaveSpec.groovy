package admin

import com.google.appengine.api.datastore.Entity
import groovyx.gaelyk.spock.ConventionalGaelykUnitSpec

class PostSaveSpec extends ConventionalGaelykUnitSpec {
	def 'posted contents are interpreted as markdown and saved'() {
		given:
		new Entity('posts', 'test-post').save()

		when:
		postSave.params.with {
			id = 'test-post'
			content = 'Hello **World**!'
			created = new Date().format('yyyy/MM/dd HH:mm')
		}
		postSave.get()


		then:
		def post = datastore.get('posts', 'test-post')
		post.content =~ '^<p>Hello <strong>World</strong>!</p>'
		post.markdown == 'Hello **World**!'
	}
}
