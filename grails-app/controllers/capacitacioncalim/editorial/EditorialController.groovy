package capacitacioncalim.editorial

import grails.converters.JSON

import capacitacioncalim.Auxiliar

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
            flash.error = "Error al guardar la Editorial"
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
        try{
            editorialService.update(command)
            redirect(action: "list")
            flash.message = "Editorial guardada correctamente"
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [editorialCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar la Editorial"
            Auxiliar.printearError e
            render (view: "edit", model: [editorialCommand: command])
        }

    }

    def delete(Long id) {
        try{
            editorialService.delete(id)
            redirect(action: "list")
            flash.message = "Editorial eliminada correctamente"

        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [editorialCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar la Editorial"
            Auxiliar.printearError e
            render (view: "edit", model: [editorialCommand: command])
        }
    }

    def ajaxGetEditoriales() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }

}