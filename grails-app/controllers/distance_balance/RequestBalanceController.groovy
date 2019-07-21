package distance_balance

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RequestBalanceController {

    RequestBalanceService requestBalanceService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond requestBalanceService.list(params), model:[requestBalanceCount: requestBalanceService.count()]
    }

    def show(Long id) {
        respond requestBalanceService.get(id)
    }

    def create() {
        respond new RequestBalance(params)
    }

    def save(RequestBalance requestBalance) {
        if (requestBalance == null) {
            notFound()
            return
        }

        try {
            requestBalanceService.save(requestBalance)
        } catch (ValidationException e) {
            respond requestBalance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requestBalance.label', default: 'RequestBalance'), requestBalance.id])
                redirect requestBalance
            }
            '*' { respond requestBalance, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond requestBalanceService.get(id)
    }

    def update(RequestBalance requestBalance) {
        if (requestBalance == null) {
            notFound()
            return
        }

        try {
            requestBalanceService.save(requestBalance)
        } catch (ValidationException e) {
            respond requestBalance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'requestBalance.label', default: 'RequestBalance'), requestBalance.id])
                redirect requestBalance
            }
            '*'{ respond requestBalance, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        requestBalanceService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'requestBalance.label', default: 'RequestBalance'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestBalance.label', default: 'RequestBalance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
