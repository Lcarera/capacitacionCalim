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

    def save(String nombre, String direccion, Integer anoCreacion) {
        editorialService.save(nombre, direccion, anoCreacion)
        redirect(action: "list")
    }
    
    def create() {  
    }

    def edit(Long id) {
        def editorial = editorialService.getEditorial(id)
        [editorial: editorial]
    }

    def update(Long id, String nombre, String direccion, Integer anoCreacion, ) {
        editorialService.update(id, nombre, direccion, anoCreacion)
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