package cz.actis.cms

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.omg.SendingContext.RunTime
import spock.lang.Specification
@Mock([
Article,
Category
])
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ArticleService)
class ArticleServiceSpec extends Specification {
    private def article1
    private def article2
    private def article3
    private def category
    private def category2

    def setup() {
        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        category = new Category(name: 'Produkty', urlId: 'produkty').save()
        category2 = new Category(name: 'Produkty', urlId: 'produkty').save()
        article1 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today,articleUrl: 'ccc').save()
        article2 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category, datePublished: yesterday, dateArchived: today, articleState: ArticleState.PUBLISHED,articleUrl: 'aaa').save()
        article3 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: category2, datePublished: yesterday, dateArchived: today, articleState: ArticleState.PUBLISHED,articleUrl: 'bbb').save()
    }

    def cleanup() {
    }

    void "Method archiveAllArticles should transfer all Articles to state Archived"() {
        when: 'method archiveAllArticles() is called'
        service.archiveAllArticles()
        def articleList = Article.list()

        then: 'all Articles should be in state ARCHIVED'
        articleList != null
        articleList.size() == 3
        articleList.every{it.articleState == ArticleState.ARCHIVATED}
    }

    void "Method loadAllPublishedArticles() should return all Articles in state PUBLISHED"(){
        when: 'loadAllPublishedArticles() is called'
        def articleList =service.loadAllPublishedArticles()

        then: 'all Articles in state PUBLISHED'
        articleList != null
        articleList.size() == 2
        articleList.every{it.articleState == ArticleState.PUBLISHED}
    }
    void 'Command with valid data correctly updates Article and Category'(){
        setup:
        def command = new ArticleCommand(id: article1.id, title: 'New title', body: 'new body', categoryName: 'Produkty 2' )

        when: 'updateArticleAndCategory() is called'
        service.updateArticleAndCategory(command)
        def article = Article.findById article1.id
        def cat = Category.findById category.id

        then: 'Article & Category are correctly updated'
        article.title == 'New title'
        article.body == 'new body'
        cat.name == 'Produkty 2'

    }

    void 'Command with incorrect data throws RuntimeException'(){
        setup:
        def command = new ArticleCommand(id: 12345L)
        when: 'update ArticleAndCategory is called'
        service.updateArticleAndCategory(command)

        then: 'RuntimeException is thrown'
        def e = thrown(RuntimeException)
        e.getMessage() == 'article.category.save.failed'


    }


}
