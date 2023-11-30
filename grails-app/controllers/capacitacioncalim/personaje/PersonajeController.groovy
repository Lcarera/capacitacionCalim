package capacitacioncalim.personaje
<<<<<<< HEAD
import capacitacioncalim.arma.Arma
import capacitacioncalim.Auxiliar
import grails.converters.JSON

class PersonajeController{

    def PersonajeService

    def ajaxGetPersonajes() {
        def personajes = personajeService.getPersonajesSQL()
        render personajes as JSON
    }

    def list() {
    }

    def create() {  
    }

    def ajaxGetArmas() {
        def armas = personajeService.listArmas()
        render armas as JSON
    }
    
    def calcularMasPoderoso() {
        def todosLosPersonajes = Personaje.list()
        def personajeMasPoderoso = null
        def maxPuntos = 0

        todosLosPersonajes.each { personaje ->
            def puntosDeFuerza = personaje.puntosFuerza
            def puntosDeAtaque = personaje.arma.puntosAtaque
            def totalPuntos = puntosDeFuerza + puntosDeAtaque

            if (totalPuntos > maxPuntos) {
                maxPuntos = totalPuntos
                personajeMasPoderoso = personaje
            }
        }

        if (personajeMasPoderoso) {
        def mensaje = "El personaje más poderoso es ${personajeMasPoderoso.nombre} con ${maxPuntos} puntos de fuerza y ataque combinados."
        render "Swal.fire('${mensaje}');"
        } else {
        render "Swal.fire('No hay personajes creados.');"
         }
    }



    def save(PersonajeCommand command) {
        try{
            personajeService.save(command)
=======

import capacitacioncalim.Auxiliar

import grails.converters.JSON

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PersonajeController {

    def personajeService

    def index() {
        render (view: 'list')
    }

    def list() {}

    def create() {}

    def save(PersonajeCommand personajeCommand) {
        try{
            personajeService.save(personajeCommand)
>>>>>>> main
            flash.message = "Personaje guardado correctamente"
            redirect(action: "list")
        }
        catch(AssertionError e) {
<<<<<<< HEAD
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "create", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "create", model: [personajeCommand: command])
        }

    }

    def edit(Long id) {
        def personajeCommand = personajeService.getPersonajeCommand(id)
        [personajeCommand: personajeCommand]
=======
            flash.message = e.message.split("finerror")[0]
            Auxiliar.printearError e
            render(view: "create", model: [personajeCommand: personajeCommand])
        }
        catch(Exception e){
            flash.message = "Error al guardar el personaje"
            Auxiliar.printearError e
            render(view: "create", model: [personajeCommand: personajeCommand])
        }
    }

    def edit(Long id) {
        [personajeCommand: personajeService.getPersonajeCommand(id)]
>>>>>>> main
    }

    def update(PersonajeCommand command) {
        try{
            personajeService.update(command)
<<<<<<< HEAD
            flash.message = "Personaje actualizado correctamente"
            redirect(action: "list")
=======
            redirect(action: "list")
            flash.message = "Personaje guardado"
>>>>>>> main
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
<<<<<<< HEAD
            flash.error = "Error al actualizar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
=======
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    
>>>>>>> main
    }

    def delete(Long id) {
        try{
            personajeService.delete(id)
<<<<<<< HEAD
            flash.message = "personaje eliminado correctamente"
            redirect(action: "list")
        }
        catch(Exception e){
            flash.error = "Error al eliminar el personaje"
            Auxiliar.printearError e
            render (view: "list", model: [personajeCommand: command])
        }
    }

=======
            redirect(action: "list")
            flash.message = "Personaje borrado"
        }
        catch(AssertionError e) {
            Auxiliar.printearError e
            flash.error = e.message.split("finerror")[0]
            render (view: "edit", model: [personajeCommand: command])
        }
        catch(Exception e){
            flash.error = "Error al guardar el personaje"
            Auxiliar.printearError e
            render (view: "edit", model: [personajeCommand: command])
        }
    }

    def ajaxGetPersonajes() {
        def personajes = personajeService.listPersonajes()
        render personajes as JSON
    }

    def ajaxGetPersonajeMasPoderoso() {
        def personaje = personajeService.getPersonajeMasPoderoso()
        render personaje as JSON
    }
>>>>>>> main

}