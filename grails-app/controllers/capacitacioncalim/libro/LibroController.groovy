package capacitacioncalim.libro

import grails.converters.JSON

class LibroController {

    def libroService

    def prueba() {
        render "Hola mundo"
    }

    def listJson() {
        def libros = libroService.listLibros()
        render libros as JSON
    }

    def list() {
        def libros = libroService.listLibros()
        [libros: libros]
    }

    def create() {  
    }

    def save(LibroCommand command) {
        libroService.save(command)
        redirect(action: "list")
    }

    def edit(Long id) {
        def libro = libroService.getLibroCommand(id)
        [libro: libro, editorialId: libro.editorial.id]
    }

    def update(LibroCommand command) {
        libroService.update(command)
        redirect(action: "list")
    }

    def delete(Long id) {   
        libroService.delete(id)
        redirect(action: "list")
    }

    def getLibrosEditorialMessi() {
        def libros = libroService.getLibrosByEditorial(10)
        render libros as JSON
    }
    
    def ajaxGetLibros() {
        def libros = libroService.listLibros()
        render libros as JSON
    }
}