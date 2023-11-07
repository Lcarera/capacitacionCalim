package capacitacioncalim.inicializacion

import grails.converters.JSON

import capacitacioncalim.arma.Arma
import capacitacioncalim.personaje.Personaje

class JsonInicializacion {
    static def inicializar(){
        JSON.registerObjectMarshaller(Arma){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['nombre'] = it.nombre
            returnArray['puntosAtaque'] = it.puntosAtaque

            return returnArray
        }

        JSON.registerObjectMarshaller(Personaje){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['nombre'] = it.nombre
            returnArray['puntosSalud'] = it.puntosSalud
            returnArray['puntosFuerza'] = it.puntosFuerza
            returnArray['fechaCreacion'] = it.fechaCreacion
            returnArray['gritoGuerra'] = it.gritoGuerra

            return returnArray
        }
    }
}