package distance_balance

import grails.gorm.services.Service

@Service(Hospital)
interface HospitalService {

    Hospital get(Serializable id)

    List<Hospital> list(Map args)

    Long count()

    void delete(Serializable id)

    Hospital save(Hospital hospital)

}