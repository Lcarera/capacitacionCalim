package testchromedriver.selenium

import grails.validation.Validateable

class BuscarEnVideoCommand implements Validateable{
    String buscarPor
    String busqueda

    static constraints = {
        buscarPor nullable: false
        busqueda nullable: false
    }
} 