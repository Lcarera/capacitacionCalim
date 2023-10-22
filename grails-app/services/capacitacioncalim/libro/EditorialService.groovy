package capacitacioncalim.editorial

import grails.transaction.Transactional

@Transactional
class EditorialService {
    
    public List<Editorial> listEditoriales() {
        return Editorial.list()
    }

    public Editorial save(String nombre, String direccion, Integer anocreacion) {
        Editorial editorial = new Editorial(nombre: nombre, direccion: direccion, anocreacion: anocreacion)
        editorial.save(flush:true)
        return editorial
    }

    public Editorial getEditorial(Long id) {
        return Editorial.get(id)
    }

    public Editorial update(Long id, String nombre, String direccion, Integer anocreacion) {
        Editorial editorial = Editorial.get(id)
        editorial.nombre = nombre
        editorial.direccion = direccion
        editorial.anocreacion = anocreacion
        editorial.save(flush:true)
        return editorial
    }

    public Editorial delete(Long id) {
        Editorial editorial = Editorial.get(id)
        editorial.delete(flush:true)
        return editorial
    }
}