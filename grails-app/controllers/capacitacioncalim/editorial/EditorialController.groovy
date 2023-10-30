package capacitacioncalim.editorial
import grails.converters.JSON
class EditorialController {

    def editorialService

    def list() {
        def editoriales = editorialService.listEditoriales()
        [editoriales: editoriales]
    }
    def listJson() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }

    def save(EditorialCommand command) {
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
    
    def create() {  
    }

    def edit(Long id) {
        def editorial = editorialService.getEditorialCommand(id)
        [editorial: editorial]
    }

    def update(EditorialCommand command) {
        editorialService.update(command)
        redirect(action: "list")
    }

    def delete(Long id) {
        editorialService.delete(id)
        redirect(action: "list")
    }

    def ajaxGetEditoriales() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }
}