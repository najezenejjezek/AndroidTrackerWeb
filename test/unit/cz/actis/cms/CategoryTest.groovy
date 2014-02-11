package cz.actis.cms

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
/**
 * Created by radek.lunak on 29.1.14.
 */

@Mock([
Category,
Article
])

@TestFor(Category)
class CategoryTest extends Specification {

    def setup(){
    }
    def cleanup(){
    }

    void 'urlId is correct'(){
        setup:
        def c =new Category(name: 'Produkty',urlId: 'produkty')

        when: 'Category is validated'
        c.validate()
        then: 'There should be no error'
        !c.hasErrors()
    }

    void 'Method loadPublished Articles() retrieves correct data'(){
        setup:
        def category = new Category(name: 'Produkty', urlId: 'produkty').save()
        def category2 = new Category(name: 'Produkty', urlId: 'produkty').save()

        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        def article1 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'aaa').save()
        def article2 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'bbb', articleState: ArticleState.PUBLISHED).save()
        def article3 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category2, datePublished: today, dateArchived: yesterday,articleUrl: 'ccc', articleState: ArticleState.PUBLISHED).save()


        when: 'method loadPublishedArticles() is called'
        def articleList = category.loadPublishedArticles()

        then: 'only published articles are retrieved'
        articleList != null
        articleList.size() == 1
        articleList.contains article2
    }


}
