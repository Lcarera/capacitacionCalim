package capacitacioncalim.inicializacion

import grails.converters.JSON

import capacitacioncalim.libro.Libro
import capacitacioncalim.editorial.Editorial

class JsonInicializacion {
    static def inicializar(){
		JSON.registerObjectMarshaller(Libro){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['titulo'] = it.titulo
            returnArray['nombreAutor'] = it.autor
            returnArray['anocreacion'] = it.ano
            returnArray['idEditorial'] = it.editorial.nombre
            
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
    }
}