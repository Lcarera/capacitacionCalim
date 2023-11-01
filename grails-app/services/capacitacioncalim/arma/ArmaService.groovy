package capacitacioncalim.arma
import grails.transaction.Transactional

@Transactional
class ArmaService {
    
    public List<Arma> listArmas() {
        Editorial.list()
    }
    
    public Editorial save(EditorialCommand command) {
        assert command.anoCreacion < 2023: "El ano debe ser menor a 2023finerror"
        def editorial = new Editorial()
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
        assert command.anoCreacion < 2023: "El ano debe ser menor a 2023finerror"
        def editorial = Editorial.get(command.id)
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
        editorialCommand.id = editorial.id
        editorialCommand.version = editorial.version
        editorialCommand.nombre = editorial.nombre 
        editorialCommand.direccion = editorial.direccion
        editorialCommand.anoCreacion = editorial.anoCreacion
        return editorialCommand
    }
}