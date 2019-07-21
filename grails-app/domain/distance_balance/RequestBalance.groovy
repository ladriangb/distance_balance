package distance_balance

class RequestBalance {
    String latitude
    String longitude
    Integer radio
    String resultado
    String minimoDesbalance

    static constraints = {
        latitude nullable: false,blank: false
        longitude nullable: false,blank: false
        radio nullable: false,blank: false
        resultado nullable: true
        minimoDesbalance nullable: true
    }
//
//    static mapping = {
//
//    }
}