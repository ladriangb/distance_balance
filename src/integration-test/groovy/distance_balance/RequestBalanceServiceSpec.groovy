package distance_balance

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RequestBalanceServiceSpec extends Specification {

    RequestBalanceService requestBalanceService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new RequestBalance(...).save(flush: true, failOnError: true)
        //new RequestBalance(...).save(flush: true, failOnError: true)
        //RequestBalance requestBalance = new RequestBalance(...).save(flush: true, failOnError: true)
        //new RequestBalance(...).save(flush: true, failOnError: true)
        //new RequestBalance(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //requestBalance.id
    }

    void "test get"() {
        setupData()

        expect:
        requestBalanceService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<RequestBalance> requestBalanceList = requestBalanceService.list(max: 2, offset: 2)

        then:
        requestBalanceList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        requestBalanceService.count() == 5
    }

    void "test delete"() {
        Long requestBalanceId = setupData()

        expect:
        requestBalanceService.count() == 5

        when:
        requestBalanceService.delete(requestBalanceId)
        sessionFactory.currentSession.flush()

        then:
        requestBalanceService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        RequestBalance requestBalance = new RequestBalance()
        requestBalanceService.save(requestBalance)

        then:
        requestBalance.id != null
    }
}
