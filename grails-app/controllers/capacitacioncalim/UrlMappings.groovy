package capacitacioncalim

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:'personaje', action:'list' )
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
