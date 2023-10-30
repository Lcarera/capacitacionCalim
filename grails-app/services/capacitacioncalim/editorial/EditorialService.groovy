package capacitacioncalim.editorial

import grails.transaction.Transactional

@Transactional
class EditorialService {
    
    public List<Editorial> listEditoriales() {
        Editorial.list()
    }
    
    public Editorial save(EditorialCommand command) {
        Editorial editorial = new Editorial(nombre: command.nombre, direccion: command.direccion, anoCreacion: command.anoCreacion)
        editorial.save(flush:true)
        return editorial
    }

    public Editorial getEditorial(Long id) {
        return Editorial.get(id)
    }

    public Editorial update(EditorialCommand command) {
        Editorial editorial = Editorial.get(id)
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
    def getEditorialCommand(Long id){
        def editoral = getEditorial(id)
        def command = new EditorialCommand()
            command.id = editoral.id
            command.titulo = editoral.nombre
            command.autor = editoral.direccion
            command.ano = editoral.anoCreacion
        return command
    }
}