package cz.actis.cms

import grails.test.mixin.TestFor
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ArticleRestController)
class ArticleRestControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Load published Articles"() {
//        SecUser.metaClass.encodePassword = {return "12345"}
//        springSecurityService.reauthenticate('john','doe')
//        springSecurityService.getCurrentUser()
        setup:
        def articlesClient = new HTTPBuilder('http://localhost:8080/test/v1/articles')
          articlesClient.request(Method.GET,ContentType.JSON,){ req ->
              headers.'Accept' = 'application/json'
              response.success = {response, reader ->
                  println reader
                  assert response.status == 200
                  assert reader.size() == 2
                  def a1 = reader.get(0)
                  println a1

              }
          }
        when:
        println "When"
        then: true
    }
}
