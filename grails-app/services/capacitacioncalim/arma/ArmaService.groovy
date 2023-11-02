package capacitacioncalim.arma

import grails.transaction.Transactional

@Transactional
class ArmaService {

    public Arma getArma(Long id) {
        return Arma.get(id)
    }

    public List<Arma> listArmas(){
        def q = "Select * from arma;"
        def armas = sessionFactory.currentSession.createSQLQuery(q).setResultTransformer(Transformers.aliasToBean(LinkedHashMap)).list().collect{
            def item = [:]
            item.id = it.id
            item.puntosDeAtaque = it.puntos_de_ataque
            return item
        }
        return armas
    } 

}