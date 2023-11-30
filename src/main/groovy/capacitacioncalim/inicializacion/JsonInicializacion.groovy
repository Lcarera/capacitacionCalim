package capacitacioncalim.inicializacion

import grails.converters.JSON

import capacitacioncalim.libro.Libro
import capacitacioncalim.editorial.Editorial
import capacitacioncalim.personaje.Personaje
import capacitacioncalim.personaje.Arma
import capacitacioncalim.User

class JsonInicializacion {
    static def inicializar(){
		JSON.registerObjectMarshaller(Libro){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['titulo'] = it.titulo
            returnArray['autor'] = it.autor
            returnArray['ano'] = it.ano
            returnArray['editorial'] = it.editorial.nombre
            
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
            returnArray['puntosFuerza'] = it.puntosFuerza
            returnArray['puntosSalud'] = it.puntosSalud
            returnArray['fechaCreacion'] = it.fechaCreacion
            returnArray['gritoGuerra'] = it.gritoGuerra
            returnArray['user'] = it.user.id
            returnArray['arma'] = it.arma.nombre
            
            return returnArray
        }
    }
}