package capacitacioncalim.libro

import grails.transaction.Transactional

@Transactional
class LibroService {
    
    public List<Libro> listLibros() {
        return Libro.list()
    }

    public Libro save(String titulo, String autor) {
        Libro libro = new Libro(titulo: titulo, autor: autor)
        libro.save(flush:true)
        return libro
    }

    public Libro getLibro(Long id) {
        return Libro.get(id)
    }

    public Libro update(Long id, String titulo, String autor) {
        Libro libro = Libro.get(id)
        libro.titulo = titulo
        libro.autor = autor
        libro.save(flush:true)
        return libro
    }

    public Libro delete(Long id) {
        Libro libro = Libro.get(id)
        libro.delete(flush:true)
        return libro
    }
}