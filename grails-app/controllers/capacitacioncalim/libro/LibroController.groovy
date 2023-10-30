package capacitacioncalim.libro

import grails.converters.JSON

class LibroController {

    def libroService

    def prueba() {
        render "Hola mundo"
    }

    def ajaxGetLibros() {
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
        libroService.update(command)
        redirect(action: "list")
    }

    def delete(Long id) {
        libroService.delete(id)
        redirect(action: "list")
    }

    def getLibrosEditorial(long id) {
        def libros = libroService.getLibrosByEditorial(id)
        render libros as JSON
    }