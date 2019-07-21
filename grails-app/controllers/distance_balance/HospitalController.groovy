package distance_balance

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HospitalController {

    HospitalService hospitalService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hospitalService.list(params), model:[hospitalCount: hospitalService.count()]
    }

    def show(Long id) {
        respond hospitalService.get(id)
    }

    def create() {
        respond new Hospital(params)
    }

    def save(Hospital hospital) {
        if (hospital == null) {
            notFound()
            return
        }

        try {
            hospitalService.save(hospital)
        } catch (ValidationException e) {
            respond hospital.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hospital.label', default: 'Hospital'), hospital.id])
                redirect hospital
            }
            '*' { respond hospital, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond hospitalService.get(id)
    }

    def update(Hospital hospital) {
        if (hospital == null) {
            notFound()
            return
        }

        try {
            hospitalService.save(hospital)
        } catch (ValidationException e) {
            respond hospital.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hospital.label', default: 'Hospital'), hospital.id])
                redirect hospital
            }
            '*'{ respond hospital, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        hospitalService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hospital.label', default: 'Hospital'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hospital.label', default: 'Hospital'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
