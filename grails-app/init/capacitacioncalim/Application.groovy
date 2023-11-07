package capacitacioncalim

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import capacitacioncalim.arma.Arma

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}