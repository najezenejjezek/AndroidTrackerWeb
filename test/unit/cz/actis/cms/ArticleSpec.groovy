package cz.actis.cms

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

import javax.xml.crypto.Data

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */

@Mock([
Article,
Category
])
@TestFor(Article)
class ArticleSpec extends Specification {
    private def category

    def setup() {
        category = new Category(name: 'Produkty', urlId: 'produkty').save()
    }

    def cleanup() {
    }

    void "Archive date must be greater than publish date"() {
        setup:
        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        def article1 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'aaa')

        def article2 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: today, dateArchived: yesterday,articleUrl: 'bbb')
        when: "Validation is called"
        article1.validate()
        article2.validate()

        then: 'No errors should occur'
        println article1
        !article1.hasErrors()
        article2.hasErrors()
        article2.errors.allErrors.size() == 1
        article2.errors.allErrors.get(0).code == 'date.archived.lower.than.published'

    }
}
