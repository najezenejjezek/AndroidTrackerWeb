import cz.actis.cms.Article
import cz.actis.cms.ArticleState
import cz.actis.cms.SecRole
import cz.actis.cms.SecUser
import cz.actis.cms.SecUserSecRole
import org.omg.CORBA.Environment
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        switch (Environment.current){
            case Environment.DEVELOPMENT:
                development()
                break
            case Environment.TEST:
                test()
                break
            case Environment.PRODUCTION:
                production()
                break
        }
    }
    def development(){
        securityData()
        def categoryProducts = new cz.actis.cms.Category(name: 'Produkty', urlId: 'produkty').save()
        def categoryServices = new cz.actis.cms.Category(name: 'Sluzby', urlId: 'sluzby').save()

        def today = Date.parse("dd.MM.yyyy","29.01.2014")
        def yesterday = Date.parse('dd.MM.yyyy',"28.01.2014")
        def article1 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: categoryProducts, datePublished: yesterday,articleUrl: 'aaa', dateArchived: today).save()
        def article2 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: categoryProducts, datePublished: yesterday,articleUrl: 'bbb', dateArchived: today, articleState: ArticleState.PUBLISHED).save()
        def article3 = new Article(title: 'Hello world', body: '<p>Hello WORLD!</p>', category: categoryServices, datePublished: yesterday,articleUrl: 'ccc', dateArchived: today, articleState: ArticleState.PUBLISHED).save()

    }
    def securityData(){
        def roleEditor = new SecRole(authority: 'ROLE_EDITOR').save()
        def editor = new SecUser(username: 'john',password: 'doe',enabled: true).save()
        SecUserSecRole.create editor, roleEditor
    }


    def test(){

    }
    def production(){

    }






    def destroy = {
    }
}
