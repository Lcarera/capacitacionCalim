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
        render editorialService.list() as JSON
    }

}