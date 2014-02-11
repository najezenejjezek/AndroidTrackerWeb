package cz.actis.cms

/**
 * Created by radek.lunak on 29.1.14.
 */
import grails.rest.Resource
//@Resource(uri = '/categories', formats=['json','xml'])
@Resource(formats=['json','xml'])

class Category {
    String name
    String urlId
    boolean isValid

    Date dateCreated
    Date lastUpdated

    static constraints ={
        name nullable: false, maxSize: 32
        urlId nullable: false, blank: false, matches: '[a-zA-Z0-9/-]*'
    }

    static mappings = {
        autoTimestamp(true)
    }

    def loadPublishedArticles(){
         Article.findAllByCategoryAndArticleState(this,ArticleState.PUBLISHED)
    }
}

