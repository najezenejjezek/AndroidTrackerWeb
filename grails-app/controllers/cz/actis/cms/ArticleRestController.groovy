package cz.actis.cms

import grails.rest.RestfulController


class ArticleRestController extends RestfulController{

    static namespace = 'v1'
    static responseFormats = ['json','xml']
    def articleService

    @Override
    def index() {
        def articleList = articleService.loadAllPublishedArticles()
        respond(articleList)
    }
}
