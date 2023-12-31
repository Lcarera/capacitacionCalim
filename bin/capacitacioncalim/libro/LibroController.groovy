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

    def save(String titulo, String autor, Integer ano) {
        libroService.save(titulo, autor, ano)
        redirect(action: "list")
    }

    def edit(Long id) {
        def libro = libroService.getLibro(id)
        [libro: libro]
    }

    def update(Long id, String titulo, String autor, Integer ano) {
        libroService.update(id, titulo, autor, ano)
        redirect(action: "list")
    }

    def delete(Long id) {
        libroService.delete(id)
        redirect(action: "list")
    }
}