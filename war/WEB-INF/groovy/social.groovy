import java.text.SimpleDateFormat
import groovy.json.JsonSlurper

def twitter = "http://search.twitter.com/search.atom?q=from%3Amarcinerdmann&rpp=200".toURL().get(async: true)

def items = []

def sdf
def slurper = new XmlSlurper()

try {
    // date format: 2011-07-29T09:00:10Z
    sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    def twitterDoc = slurper.parseText(twitter.get().text)
    twitterDoc.entry.each { entry ->
        items << [
                origin: 'twitter',
                title: entry.title.text(),
                published: sdf.parse(entry.published.text()),
                link: entry.link.find { it.@type == 'text/html' }.@href.text()
        ]
    }
} catch (any) {
    any.printStackTrace()
}

request.items = items.sort { it.published }.reverse().each { it.published.clearTime() }.groupBy { it.published }

forward '/WEB-INF/pages/social.gtpl'