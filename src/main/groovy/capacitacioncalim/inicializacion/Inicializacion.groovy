package capacitacioncalim.inicializacion

import capacitacioncalim.User
import capacitacioncalim.Role
import capacitacioncalim.UserRole

import capacitacioncalim.arma.Arma
import capacitacioncalim.personaje.Personaje

import org.joda.time.LocalDate

class Inicializacion {
    
    static def comienzo() {
        println "##################################################################"
        println "################# Inicializando la aplicación ####################"
        inicializarRoles()
        inicializarUsuarios()
        inicializarArmas()
        inicializarPersonajes()
    }

    private static void inicializarRoles() {
        println "Inicializando roles"
        def roles = [
            ["authority": "ROLE_ADMIN"],
            ["authority": "ROLE_USER"]
        ]
        roles.each { rol ->
            if(Role.findByAuthority(rol.authority)) return
            new Role(rol).save(flush: true, failOnError: true)
        }
    }

    private static void  inicializarArmas() {
        println "Inicializando armas"
        def armas = [
            ["nombre": "Arco", "puntosFuerza": 12],
            ["nombre": "Espada", "puntosFuerza": 20],
            ["nombre": "Martillo", "puntosFuerza": 24]
        ]
        armas.each { arma ->
            if(Arma.findByNombre(arma.nombre)) return
            new Arma(arma).save(flush: true, failOnError: true)
        }
    }

    private static void inicializarPersonajes() {
        println "Inicializando personajes"
        def personajes = [
            ["nombre": "Test1", "puntosFuerza": 12, "puntosSalud": 12, "gritoGuerra": "Peleo mucho", 
             "arma": Arma.findByNombre('Martillo'), "fechaCreacion": new LocalDate()],
            ["nombre": "Test2", "puntosFuerza": 20, "puntosSalud": 20, "gritoGuerra": "Peleo mejor", 
             "arma": Arma.findByNombre('Arco'), "fechaCreacion": new LocalDate()],
            ["nombre": "Test3", "puntosFuerza": 24, "puntosSalud": 24, "gritoGuerra": null, 
             "arma": Arma.findByNombre('Espada'), "fechaCreacion": new LocalDate()]
        ]
        personajes.each { personaje ->
            if(Personaje.findByNombre(personaje.nombre)) return
            new Personaje(personaje).save(flush: true, failOnError: true)
        }
    }

    private static void inicializarUsuarios() {
        println "Inicializando usuarios"
        def usuarios = [
            ["username": "admin", "password": "admin", "enabled": true, "accountExpired": false, 
             "accountLocked": false, "passwordExpired": false, "roles": [Role.findByAuthority('ROLE_ADMIN')]],
            ["username": "user", "password": "user", "enabled": true, "accountExpired": false, 
             "accountLocked": false, "passwordExpired": false, "roles": [Role.findByAuthority('ROLE_USER')]]
        ]
        usuarios.each { usuario ->
            if(User.findByUsername(usuario.username)) return
            def user = new User(usuario).save(flush: true, failOnError: true)
            usuario.roles.each { rol ->
                UserRole.create user, rol, true
            }
        }
    }
    
}