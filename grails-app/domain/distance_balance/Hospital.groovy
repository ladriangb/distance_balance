package distance_balance

class Hospital {
    String latitude
    String longitude
    String name
//    boolean balanced

    static constraints = {
        name nullable: false
        latitude nullable: false, blank: false
        longitude nullable: false, blank: false
    }
}