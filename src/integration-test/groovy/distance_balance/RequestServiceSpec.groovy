package distance_balance

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RequestServiceSpec extends Specification {

    RequestService requestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Request(...).save(flush: true, failOnError: true)
        //new Request(...).save(flush: true, failOnError: true)
        //Request request = new Request(...).save(flush: true, failOnError: true)
        //new Request(...).save(flush: true, failOnError: true)
        //new Request(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //request.id
    }

    void "test get"() {
        setupData()

        expect:
        requestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Request> requestList = requestService.list(max: 2, offset: 2)

        then:
        requestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        requestService.count() == 5
    }

    void "test delete"() {
        Long requestId = setupData()

        expect:
        requestService.count() == 5

        when:
        requestService.delete(requestId)
        sessionFactory.currentSession.flush()

        then:
        requestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Request request = new Request()
        requestService.save(request)

        then:
        request.id != null
    }
}
