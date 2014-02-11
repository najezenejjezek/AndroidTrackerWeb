package cz.actis.cms

class ContentController {
    def articleService

    def index() {
        ['articleList': articleService.loadAllPublishedArticles()]
    }

    def show(String articleUrl) {
        def article = Article.findByArticleUrl(articleUrl)

        if (!article) {
            response.sendError(404)
            return
        }
        def category = article.category
        String categoryName = category.name as String
        String title = article.title as String
        String body = article.body as String
        ['article': new ArticleCommand(categoryName: categoryName, title: title, body: body)]
    }

    def update() {
        if(request.method == 'POST'){
            def command = new ArticleCommand()
            bindData command, params

            try {
                if (!command.hasErrors()) {
                    articleService.updateArticleAndCategory(command)
                    flash.message = 'article.category.save.succes'
                    redirect controller: 'article', action: 'show', id: command.id
                }else{
                    flash.message = 'incorrect.data'
                }
            } catch (RuntimeException re) {
                flash.message = re.message
                render view: 'update', model: ['article': command]
            }
        }
        def article = Article.findById(params.id)
        def command = new ArticleCommand(categoryName: article.category.name, title: article.title, body: article.body, id:article.id)
        ['article': command]

    }

    def roleSwitch() {
        redirect controller: 'category', action: 'index'
    }


}
