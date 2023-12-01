package capacitacioncalim.inicializacion

import grails.converters.JSON
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import capacitacioncalim.arma.Arma
import capacitacioncalim.personaje.Personaje
import capacitacioncalim.User

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
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy")
            def returnArray = [:]

            returnArray['id'] = it.id
            returnArray['version'] = it.version
            returnArray['nombre'] = it.nombre
            returnArray['puntosSalud'] = it.puntosSalud
            returnArray['puntosFuerza'] = it.puntosFuerza
            returnArray['puntosAtaqueTotal'] = it.puntosFuerza + it.arma.puntosAtaque
            returnArray['fechaCreacion'] = formatter.print(it.fechaCreacion)
            returnArray['gritoGuerra'] = it.gritoGuerra ? it.gritoGuerra : "-"
            returnArray['arma'] = it.arma
            returnArray['user'] = it.user

            return returnArray
        }

        JSON.registerObjectMarshaller(User){
            def returnArray = [:]

            returnArray['id'] = it.id

            return returnArray
        }
    }
}