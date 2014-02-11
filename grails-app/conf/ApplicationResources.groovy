modules = {
    application {
        resource url:'js/application.js',disposition: 'body'
    }
    'jquery-anything'{
        resource url: 'css/style.css', disposition: 'head'
    }

    'public-style'{
        dependsOn 'public-common'

        resource url: 'css/style.css', disposition: 'head'
        resource url: 'css/style-mobile.css', disposition: 'head'
    }

    'public-common'{
        resource url: 'css/common.css'

    }
}