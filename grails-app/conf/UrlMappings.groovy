class UrlMappings {


	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/dologout" controller: 'logout', action: 'index'
        "/roleSwitch" controller: 'content', action: 'roleSwitch'

        "/categories"(resources: 'Category'){
            "articles"(resources: 'Article')
        }

        "/v1/articles"{
            controller = "articleRest"
            namespace = 'v1'
        }

//        "/$articleUrl" {
//            controller = 'content'
//            action = 'show'
//            constraints {
//                articleUrl (notInList: ['login','dologout'])
//            }
//        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
