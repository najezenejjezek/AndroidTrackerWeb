package cz.actis.cms


class Article {

    String title
    String body
    String articleUrl

    Category category
    ArticleState articleState = ArticleState.CONCEPT
    Date datePublished
    Date dateArchived

    Date dateCreated
    Date lastUpdated

    //static hasMany = comments:Comment

    static constraints = {
        title nullable: false, blank: false, maxSize: 128
        body nullable: false, blank: false, maxSize: 16_368
        articleUrl nullable: false, blank: false, maxSize: 64
        category nullable: false
        datePublished nullable: true
        dateArchived nullable: true, validator: dateArchivedValidator
    }

    static mapping = {
        autoTimestamp true
    }

    static dateArchivedValidator = { val,obj ->
        if(val == null){
            return true
        }
        val.compareTo(obj.datePublished) > 0 ? true : ['date.archived.lower.than.published']

    }
}
