package capacitacioncalim.inicializacion

import grails.converters.JSON

import capacitacioncalim.libro.Libro
import capacitacioncalim.editorial.Editorial
import capacitacioncalim.arma.Arma
import capacitacioncalim.personaje.Personaje


class JsonInicializacion {
    static def inicializar(){
		JSON.registerObjectMarshaller(Libro){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['titulo'] = it.titulo
            returnArray['autor'] = it.autor
            returnArray['ano'] = it.ano
            returnArray['editorialnombre'] = it.editorial
            
            return returnArray
        }

        JSON.registerObjectMarshaller(Editorial){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['nombre'] = it.nombre
            returnArray['direccion'] = it.direccion
            returnArray['anoCreacion'] = it.anoCreacion

            return returnArray
        }

        JSON.registerObjectMarshaller(Personaje){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['nombre'] = it.nombre
            returnArray['puntosFuerza'] = it.puntosFuerza
            returnArray['puntosSalud'] = it.puntosSalud
            returnArray['fecha'] = it.fecha.toString("yyyy/MM/dd")
            returnArray['gritoGuerra'] = it.gritoGuerra?: '-'
            returnArray['arma'] = it.arma
            returnArray['PoderTotal'] = it.poderTotal
            returnArray["user"] = it.user

            return returnArray
        }

        JSON.registerObjectMarshaller(Arma){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['nombre'] = it.nombre
            returnArray['puntosAtaque'] = it.puntosataque
            
            return returnArray
        }
    }
}