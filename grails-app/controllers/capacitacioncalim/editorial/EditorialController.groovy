package capacitacioncalim.editorial

import grails.converters.JSON
class EditorialController {

    def editorialService

    def index() {
    
    }

    def show() {
    
    }

    def save() {
    
    }

    def delete() {
    
    }

    def ajaxGetEditoriales() {
        def editoriales = editorialService.list()
        render editoriales as JSON
    }

}