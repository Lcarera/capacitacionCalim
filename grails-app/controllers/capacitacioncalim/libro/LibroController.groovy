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
        [libroCommand: libro]
    }

    def update(LibroCommand command) {
        libroService.update(command.id, command.titulo, command.autor, command.ano, command.editorialId)
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
}