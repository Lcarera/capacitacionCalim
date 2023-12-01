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
            flash.message = "Editorial creada correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "create", model: [editorialCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al crear la editorial"
            Auxiliar.printearError e
            render (view: "create", model: [editorialCommand: command])
        }
        
    }
    
    def create() {  
    }

    def edit(Long id) {
        def editorialCommand = editorialService.getEditorialCommand(id)
        [editorialCommand: editorialCommand]
    }

    def update(EditorialCommand command) {
        try {
            editorialService.update(command)
            flash.message = "Editorial actualizada correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [editorialCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al actualizar la editorial"
            Auxiliar.printearError e
            render (view: "edit", model: [editorialCommand: command])
        }
        
    }

    def delete(Long id) {
        try {
            editorialService.delete(id)
            flash.message = "Editorial eliminada correctamente"
            redirect(action: "list")
        }
        catch(Exception e){
            flash.error = "Error al eliminar la editorial"
            Auxiliar.printearError e
            redirect(action: "edit", params: [id: id])
        }
    }

    def ajaxGetEditoriales() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }

}