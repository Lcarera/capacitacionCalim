package capacitacioncalim.arma

import grails.transaction.Transactional
import java.util.LinkedHashMap
import org.hibernate.transform.Transformers

@Transactional
class ArmaService {

    def sessionFactory

    public Arma getArma(Long id) {
        return Arma.get(id)
    }

    public List<Arma> listArmas(){
        def q = "select * from arma;"
        def armas = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre
            item.puntosDeAtaque = it.puntos_de_ataque
            return item
        }
        return armas
    } 

}