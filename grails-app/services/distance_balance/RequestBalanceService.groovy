package distance_balance

import grails.gorm.services.Service

@Service(RequestBalance)
interface RequestBalanceService {

    RequestBalance get(Serializable id)

    List<RequestBalance> list(Map args)

    Long count()

    void delete(Serializable id)

    RequestBalance save(RequestBalance requestBalance)

}