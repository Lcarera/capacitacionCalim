package capacitacioncalim.arma
import grails.transaction.Transactional

@Transactional
class ArmaService {
    
    public List<Arma> listArmas() {
        Arma.list()
    }
    

    public Arma getArma(Long id) {
        return Arma.get(id)
    }


}