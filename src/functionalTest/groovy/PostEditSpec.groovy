import geb.spock.GebSpec
import groovyx.gaelyk.functional.geb.DataSetupCategory
import groovyx.gaelyk.functional.geb.LoginCategory
import groovyx.gaelyk.functional.spock.ModifiesDatastore
import page.PostEditPage

@ModifiesDatastore
@Mixin(LoginCategory)
@Mixin(DataSetupCategory)
class PostEditSpec extends GebSpec {
	void 'post contents are displayed as markdown'() {
		given:
		setupData {
			posts('test-post') {
				markdown = 'Hello **World**!'
				content = '<p>Hello <strong>World</strong>!</p>'
				created = new Date()
			}
		}

		when:
		loginTo 'admin@proxerd.pl', true, PostEditPage, 'test-post'

		then:
		postContents == 'Hello **World**!'
	}
}
