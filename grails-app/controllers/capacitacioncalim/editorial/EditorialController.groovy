package capacitacioncalim.editorial

import grails.converters.JSON

class EditorialController {

    def editorialService

    def prueba() {
        render "Hola mundo"
    }

    def listJson() {
        def editoriales = editorialService.listEditoriales()
        render editoriales as JSON
    }

    def list() {
        def editoriales = editorialService.listEditoriales()
        [editoriales: editoriales]
    }

    def create() {  
    }

    def save(String nombre, String direccion, Integer anocreacion) {
        editorialService.save(nombre, direccion, anocreacion)
        redirect(action: "list")
    }

    def edit(Long id) {
        def editorial = editorialService.getEditorial(id)
        [editorial: editorial]
    }

    def update(Long id, String nombre, String direccion, Integer anocreacion) {
        editorialService.update(id, nombre, direccion, anocreacion)
        redirect(action: "list")
    }

    def delete(Long id) {
        editorialService.delete(id)
        redirect(action: "list")
    }
}