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
        editorialService.save(command)
        redirect(action: "list")
    }
    
    def create() {  
    }

    def edit(Long id) {
        def editorial = editorialService.ge(id)
        [editorialCommand: editorial]
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