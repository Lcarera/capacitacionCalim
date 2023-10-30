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
        //editorialService.save(command)
        //redirect(action: "list")
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
        def editorialCommand = editorialService.getEditorialCommand(id)
        [editorialCommand: editorialCommand]
    }

    def update(EditorialCommand command) {
        //editorialService.update(command)
        //redirect(action: "list")
        try{
            editorialService.update(command)
            flash.message = "Editorial actualizada correctamente"
            redirect(action: "list")
        }
        catch(Exception e){
            flash.error = "Error al actualizar la editorial"
            render (view: "edit", model: [editorialCommand: command])
        }
    }

    def delete(Long id) {
        //editorialService.delete(id)
        //redirect(action: "list")
        try{
            editorialService.delete(id)
            flash.message = "Editorial borrada correctamente"
            redirect(action: "list")
        }
        catch(Exception e){
            flash.error = "Error al borrar la editorial"
            Auxiliar.printearError e
            render (view: "list", model: [editorialCommand: command])
        }
    }

    def ajaxGetEditoriales() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }

}