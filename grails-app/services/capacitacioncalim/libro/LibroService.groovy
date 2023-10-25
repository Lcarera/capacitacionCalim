package capacitacioncalim.libro

import capacitacioncalim.editorial.Editorial
import capacitacioncalim.editorial.EditorialService
import grails.transaction.Transactional

@Transactional
class LibroService {

    def editorialService
    
    public List<Libro> listLibros() {
        return Libro.list()
    }

    public Libro save(String titulo, String autor, Integer ano, Long editorialId) {
        Editorial editorial = editorialService.getEditorial(editorialId)
        Libro libro = new Libro(titulo: titulo, autor: autor, ano: ano, editorial: editorial)
        libro.save(flush:true, failOnError:true)
        return libro
    }

    public Libro getLibro(Long id) {
        return Libro.get(id)
    }

    public Libro update(Long id, String titulo, String autor, Integer ano, Long editorialId) {
        Libro libro = Libro.get(id)
        libro.titulo = titulo
        libro.autor = autor
        libro.ano = ano
        Editorial editorial = editorialService.getEditorial(editorialId)
        libro.editorial = editorial     
        libro.save(flush:true)
        return libro
    }

    public Libro delete(Long id) {
        Libro libro = Libro.get(id)
        libro.delete(flush:true)
        return libro
    }


    def getLibrosByEditorial(Long editorialId) {
        def editorial = editorialService.getEditorial(editorialId)
        def libros = Libro.findAllByEditorial(editorial)
        return libros
    }
}