package capacitacioncalim.personaje
import capacitacioncalim.User


import org.joda.time.LocalDate


class Personaje {


   String nombre
   Long puntosFuerza
   Long puntosSalud
   String gritoGuerra


   LocalDate fechaCreacion


   Arma arma
   static belongsTo = [user: User]


   static constraints = {
       nombre nullable: false
       puntosFuerza nullable: false
       puntosSalud nullable: false
       gritoGuerra nullable: true
       fechaCreacion nullable: false
       arma nullable: false
       user nullable: false
   }


}
