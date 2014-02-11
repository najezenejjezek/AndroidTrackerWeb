package cz.actis.cms

import grails.validation.Validateable

/**
 * Created by radek.lunak on 30.1.14.
 */

@Validateable
class ArticleCommand {
    Long id

    String categoryName
    String title
    String body

    static constraints = {
        id nullable: false, blank: false
        categoryName nullable: false,maxSize: 32
        title nullable: false,blank: false,maxSize: 128
        body nullable: false,blank: false, maxSize: 16_384
    }





}
