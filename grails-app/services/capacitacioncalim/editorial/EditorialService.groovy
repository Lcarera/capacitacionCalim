package capacitacioncalim.editorial

import grails.transaction.Transactional
import capacitacioncalim.libro.LibroService

@Transactional
class EditorialService {
    def libroService

    public List<Editorial> listEditoriales() {
        Editorial.list()
    }
    
    public Editorial save(command) {
        assert command.anoCreacion < 2023: "EL año debe ser menor a 2023finerror"

        Editorial editorial = new Editorial(nombre: command.nombre, direccion: command.direccion, anoCreacion: command.anoCreacion)
        editorial.save(flush:true)
        return editorial
    }

    def getEditorialCommand(Long id) {
        def editorial = Editorial.get(id)
        def editorialCommand = new EditorialCommand()
        editorialCommand.id = editorial.id
        editorialCommand.version = editorial.version
        editorialCommand.nombre = editorial.nombre 
        editorialCommand.direccion = editorial.direccion
        editorialCommand.anoCreacion = editorial.anoCreacion

        return editorialCommand
    }

    public Editorial getEditorial(Long id) {
        return Editorial.get(id)
    }

    public Editorial update(command) {
        assert command.anoCreacion < 2023: "El ano de creacion debe ser menor a 2023finerror"

        Editorial editorial = Editorial.get(command.id)
        editorial.nombre = command.nombre
        editorial.direccion = command.direccion
        editorial.anoCreacion = command.anoCreacion
        editorial.save(flush:true)
        return editorial
    }

    public Editorial delete(Long id) {
        assert libroService.getLibrosByEditorial(id).size() == 0 : "La editorial no debe tener libros registradosfinerror"

        Editorial editorial = Editorial.get(id)
        editorial.delete(flush:true)
        return editorial
    }
}