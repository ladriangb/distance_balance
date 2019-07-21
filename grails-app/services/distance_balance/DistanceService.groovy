package distance_balance

class DistanceService {
    def isUnbalanced={radius, item->
        return  item>radius
    }

    def distances(Map params) {
        def hospitales = params.hospitales
        def distances = hospitales.collect {
            def lat = Double.parseDouble(it.latitude)
            def lng = Double.parseDouble(it.longitude)
            def pLat = Double.parseDouble(params.lat)
            def pLng = Double.parseDouble(params.lng)
            distance(lat, lng, pLat, pLng)
        }
        distances
    }

    def desbalances(Map params) {

        params.hospitales = Hospital.list().collect {
            [latitude: it.latitude, longitude: it.longitude]
        }
        def result = distances(params)
        def radius = Double.parseDouble(params.radius)
//        def D = result.findAll { it > radius }
        def D = result.findAll { isUnbalanced( radius,it) }
        def B = result - D
        def sD = D.sum() ?: 0
        def sB = B.sum() ?: 0
        def calcule = []
        for (int i = 0; i < result.size(); i++) {
            for (int j = i + 1; j < result.size(); j++) {
                calcule.add(sD * sB * Math.abs(result[i] - result[j]))
            }
        }

        def requestBalance = new RequestBalance([
                latitude        : params.lat,
                longitude       : params.lng,
                radio           : params.radius,
                resultado       : calcule,
                params          : params.toString(),
                minimoDesbalance: calcule.min()
        ])
        requestBalance.save()
        calcule.min()
    }

    private static final double EQUATORIAL_EARTH_RADIUS = 6378.1370;
    private static final double DEG_TO_RAD = Math.PI / 180;

    double distance(double lat1, double lon1, double lat2, double lon2) {
        double dlong = (lon2 - lon1) * DEG_TO_RAD;
        double dlat = (lat2 - lat1) * DEG_TO_RAD;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1 * DEG_TO_RAD) * Math.cos(lat2 * DEG_TO_RAD) * Math.pow(Math.sin(dlong / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = EQUATORIAL_EARTH_RADIUS * c;
        return Math.abs(d * 1000);
    }
}