package capacitacioncalim.editorial
import capacitacioncalim.libro.Libro
import grails.converters.JSON
import capacitacioncalim.Auxiliar

class EditorialController {

    def editorialService
    def libroService

    def list() {
        def editoriales = editorialService.listEditoriales()
        [editoriales: editoriales]
    }
    def listJson() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }

    def save(EditorialCommand command) {
        if (command.anoCreacion < 0) {
            flash.error = "El año de la editorial no puede ser menor que 0!"
            render(view: "create", model: [editorialCommand: command])
        } else {
            try{
                editorialService.save(command)
                flash.message = "Editorial guardada correctamente"
                redirect(action: "list")
            }
            catch(AssertionError e) {
                Auxiliar.printearError e
                flash.error = e.message.split("finerror")[0]
                render (view: "create", model: [editorialCommand: command])
            }
            catch(Exception e){
                flash.error = "Error al guardar la editorial"
                Auxiliar.printearError e
                render (view: "create", model: [editorialCommand: command])
            }
        }
    }
    
    def create() {  
    }

    def edit(Long id) {
        def editorialCommand = editorialService.getEditorialCommand(id)
        [editorialCommand: editorialCommand]
    }

    def update(EditorialCommand command) {
        if (command.anoCreacion < 0) {
            flash.error = "El año de la editorial no puede ser menor que 0!"
            render(view: "create", model: [editorialCommand: command])
        } else {
            try{
                editorialService.update(command)
                flash.message = "Editorial actualizada correctamente"
                redirect(action: "list")
            }
            catch(AssertionError e) {
                Auxiliar.printearError e
                flash.error = e.message.split("finerror")[0]
                render (view: "create", model: [editorialCommand: command])
            }
            catch(Exception e){
                flash.error = "Error al actualizar el libro"
                Auxiliar.printearError e
                render (view: "create", model: [editorialCommand: command])
            }
        }
    }

    def delete(Long id) {
        // editorialService.delete(id)
        // redirect(action: "list")
        
        def editorial = editorialService.getEditorial(id)
        if (editorial) {
            def libros = libroService.getLibrosByEditorial(editorial)
            if (libros && !libros.isEmpty()) {
                flash.error = "No puedes eliminar esta editorial porque tiene libros asociados."
            } else {
                editorialService.delete(id)
                flash.message = "Editorial eliminada correctamente"
            }
        } else {
            flash.error = "Editorial no encontrada"
        }
        redirect(action: "list")
    }

    def ajaxGetEditoriales() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }
}