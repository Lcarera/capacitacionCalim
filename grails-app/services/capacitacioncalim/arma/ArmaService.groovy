package capacitacioncalim.arma

import grails.transaction.Transactional
import org.hibernate.transform.Transformers
import java.util.LinkedHashMap

@Transactional
class ArmaService {
    def sessionFactory

    def listArmas() {
        String query = """
            SELECT id, nombre, puntos_ataque FROM arma;
        """

        def armas = sessionFactory.currentSession.createSQLQuery(query).setResultTransformer(
            Transformers.aliasToBean(LinkedHashMap)
        ).list().collect {
            def item = [:]
            item.id = it.id
            item.nombre = it.nombre
            item.puntosAtaque = it['puntos_ataque']

            return item
        }

        return armas
    }

    public Arma getArma(Long id) {
        return Arma.get(id)
    }
}