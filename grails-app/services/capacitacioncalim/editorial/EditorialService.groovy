package capacitacioncalim.editorial

import grails.transaction.Transactional

@Transactional
class EditorialService {
    
    public List<Editorial> listEditoriales() {
        Editorial.list()
    }
    
    public Editorial save(EditorialCommand command) {
        Editorial editorial = Editorial.get(command.id)
        editorial.nombre = command.nombre
        editorial.direccion = command.direccion
        editorial.anoCreacion = command.anoCreacion
        editorial.save(flush:true)
        return editorial
    }

    public Editorial getEditorial(Long id) {
        return Editorial.get(id)
    }

    public Editorial update(EditorialCommand command) {
        Editorial editorial = Editorial.get(command.id)
        editorial.nombre = command.nombre
        editorial.direccion = command.direccion
        editorial.anoCreacion = command.anoCreacion
        editorial.save(flush:true)
        return editorial
    }

    public Editorial delete(Long id) {
        Editorial editorial = Editorial.get(id)
        editorial.delete(flush:true)
        return editorial
    }

    def getEditorialCommand(Long id) {
        def editorial = Editorial.get(id)
        def editorialCommand = new EditorialCommand()
        editorialCommand.id = libro.id
        editorialCommand.version = libro.version
        editorialCommand.nombre = libro.nombre 
        editorialCommand.direccion = libro.direccion
        editorialCommand.anoCreacion = libro.anoCreacion
        return editorialCommand
    }
}