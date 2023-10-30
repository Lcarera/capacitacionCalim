package capacitacioncalim.libro

import capacitacioncalim.Auxiliar
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
        try{
            libroService.save(command)
            flash.message = "Libro guardado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "create", model: [libroCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el libro"
            Auxiliar.printearError e
            render (view: "create", model: [libroCommand: command])
        }
    }

    def edit(Long id) {
        def libroCommand = libroService.getLibroCommand(id)
        [libroCommand: libroCommand]
    }

    def update(LibroCommand command) {
        try{
            libroService.update(command)
            flash.message = "Libro editado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [libroCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al editar el libro"
            Auxiliar.printearError e
            render (view: "edit", model: [libroCommand: command])
        }
    }

    def delete(Long id) {
        try {
            libroService.delete(id)
            flash.message = "Libro eliminado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            redirect(action: "edit", params: ["id": id])
        }
        catch(Exception e) {
            flash.error = "Error al eliminar el libro"
            Auxiliar.printearError e
            redirect(action: "edit", params: ["id": id])
        }
    }

    def ajaxGetLibros() {
        def libros = libroService.listLibros()
        render libros as JSON
    }

}