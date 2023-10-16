package capacitacioncalim.libro

import grails.transaction.Transactional

@Transactional
class LibroService {
    
    public List<Libro> listLibros() {
        return Libro.list()
    }
}