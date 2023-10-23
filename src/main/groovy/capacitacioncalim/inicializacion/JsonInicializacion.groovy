package capacitacioncalim.inicializacion
import grails.converters.JSON
import capacitacioncalim.libro.Libro

class JsonInicializacion {
    static def inicializar(){
		JSON.registerObjectMarshaller(Libro){
			def returnArray = [:]
            returnArray['id'] = it.id
            returnArray['titulo'] = it.titulo
            returnArray['autor'] = it.autor

            return returnArray
        }
    }
}