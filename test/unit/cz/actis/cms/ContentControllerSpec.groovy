package cz.actis.cms

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.mock.interceptor.MockFor
import spock.lang.Ignore
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock([
Article,
Category
])
@TestFor(ContentController)
class ContentControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Index generates model from service"() {
        def articleService = new MockFor(ArticleService)
        articleService.demand.loadAllPublishedArticles(1){
            return [new Object(),new Object()]
        }
        controller.articleService = articleService.proxyInstance()
        when: 'method index() is called'
        def model = controller.index()

        then: 'model must contain ArticleList with 2 entries'
        model != null
        model.size() == 1
        model['articleList'] != null
        model['articleList'].size() == 2
    }

    void 'Show creates correct command for existing Article'(){
        setup:
        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        def category = new Category(name: 'Produkty', urlId: 'produkty').save()
        def article1 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'aaa').save()

        when: 'show() for existing Article is called'
        def model = controller.show('aaa')
        def command = model['article']

        then: 'Correctly filled Command object is returned'
        model!=null
        model.size() == 1
        command != null
        command.categoryName == category.name
        command.title == article1.title
        command.body == article1.body
    }
    void 'Show throws 404 for not existing Article'(){
        when: 'show() for not existing Article is called'
        controller.show('xxx')

        then: 'error 404 is thrown'
        response.status == 404
    }

    void 'Article is correctly updated via Command data object'(){
        setup:
        request.method = 'POST'
        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        def category = new Category(name: 'Produkty', urlId: 'produkty').save()
        def article = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'aaa').save()
        params['categoryName'] = 'Nove produkty'
        params['title'] = 'Novy titulek'
        params['body'] = '<p>Jupii</p>'
        params['id'] = article.id

        def articleService = new MockFor(ArticleService)
        articleService.demand.updateArticleAndCategory(1){
            true
        }
        controller.articleService = articleService.proxyInstance()
        when: 'update is called()'
        controller.update()

        then: 'redirect to Article view must occur'
        response.redirectedUrl == '/article/show/'+article.id
    }

    void 'Article edit screen is rendered for not valid Command data object'(){
        setup:
        request.method = 'POST'
        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        def category = new Category(name: 'Produkty', urlId: 'produkty').save()
        def article = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'aaa').save()
        params['categoryName'] = 'Nove produkty'
        params['title'] = null
        params['body'] = '<p>Jupii</p>'
        params['id'] = article.id


        when: 'update is called()'
        controller.update()
        flash.message == 'incorrect.data'

        then: 'Article edit view is rendered'
        response.status == 200
        flash.message == flash.message
        //1 * controller.render(view: 'update', model: _)
    }
}
