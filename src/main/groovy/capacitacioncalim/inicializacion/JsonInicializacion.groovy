package capacitacioncalim.inicializacion

import grails.converters.JSON

import capacitacioncalim.libro.Libro
import capacitacioncalim.editorial.Editorial
import capacitacioncalim.personaje.Personaje
import capacitacioncalim.arma.Arma

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
            returnArray['puntosDeAtaque'] = it.puntosDeAtaque
            
            return returnArray
        }

        JSON.registerObjectMarshaller(Personaje){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['nombre'] = it.nombre
            returnArray['puntosDeFuerza'] = it.puntosDeFuerza
            returnArray['fechaCreacion'] = it.fechaCreacion
            returnArray['gritoDeGuerra'] = it.gritoDeGuerra
            returnArray['arma'] = it.arma.nombre
            
            return returnArray
        }
    }
}