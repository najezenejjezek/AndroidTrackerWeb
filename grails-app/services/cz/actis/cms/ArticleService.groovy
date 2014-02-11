package cz.actis.cms

import grails.transaction.Transactional

@Transactional
class ArticleService {

    def archiveAllArticles() {
        Article.list().each {
            it.articleState = ArticleState.ARCHIVATED
            it.save()
        }
    }
    def loadAllPublishedArticles(){
        Article.findAllByArticleState(ArticleState.PUBLISHED)
    }

    def updateArticleAndCategory(ArticleCommand articleCommand){
        def article = Article.findById articleCommand.id
        if(article){
            article.title = articleCommand.title
            article.body = articleCommand.body
            def category = article.category
            category.name = articleCommand.categoryName
            category.save()
            article.save()
        }else{
            throw new RuntimeException('article.category.save.failed')
        }
    }
}
