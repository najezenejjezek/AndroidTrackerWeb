package cz.actis.cms

import org.apache.taglibs.standard.tag.el.fmt.FormatDateTag

/**
 * Created by radek.lunak on 29.1.14.
 */
class CmsTagLib {
    static namespace = "cms"

    static defaultEncodeAs = 'html'

    def  currentDate = {params->
        def data = []
        //out << g.formatDate(date:new Date(),format: 'dd.MM.yyyy')
        out << g.formatDate(date:params.date,format: 'dd.MM.yyyy')
    }
}
