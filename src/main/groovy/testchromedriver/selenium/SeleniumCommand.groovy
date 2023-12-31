package testchromedriver.selenium

import grails.validation.Validateable

class SeleniumCommand implements Validateable{
    String buscarPor
    String busqueda

    static constraints = {
        buscarPor nullable: false
        busqueda nullable: false
    }
} 