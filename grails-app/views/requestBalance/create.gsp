<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'requestBalance.label', default: 'RequestBalance')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>

    <style>
    /* Always set the map height explicitly to define the size of the div
     * element that contains the map. */
    #map {
        height: 80%;
    }

    /* Optional: Makes the sample page fill the window. */
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
    </style>
    <script>
        // This example requires the Places library. Include the libraries=places
        // parameter when you first load the API. For example:
        // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

        var map;
        var chicago = {lat: 41.85, lng: -87.65};
        var cityCircle;
        var markers = [];

        /**
         * The CenterControl adds a control to the map that recenters the map on
         * Chicago.
         * This constructor takes the control DIV as an argument.
         * @constructor
         */
        /**
         * The CenterControl adds a control to the map that recenters the map on
         * Chicago.
         * This constructor takes the control DIV as an argument.
         * @constructor
         */
        function CenterControl(controlDiv, map) {

            // Set CSS for the control border.
            var controlUI = document.createElement('div');
            controlUI.style.backgroundColor = '#fff';
            controlUI.style.border = '2px solid #fff';
            controlUI.style.borderRadius = '3px';
            controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
            controlUI.style.cursor = 'pointer';
            controlUI.style.marginBottom = '22px';
            controlUI.style.marginTop = '10px';
            controlUI.style.textAlign = 'center';
            controlUI.title = 'Click to recenter the map';
            controlDiv.appendChild(controlUI);

            // Set CSS for the control interior.
            var controlText = document.createElement('div');
            controlText.style.color = 'rgb(25,25,25)';
            controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
            controlText.style.fontSize = '16px';
            controlText.style.lineHeight = '38px';
            controlText.style.paddingLeft = '5px';
            controlText.style.paddingRight = '5px';
            controlText.innerHTML = 'Center Map';
            controlUI.appendChild(controlText);

            // Setup the click event listeners: simply set the map to Chicago.
            controlUI.addEventListener('click', function () {
                loadDoc();
            });

        }

        function loadDoc() {
            var xhttp = new XMLHttpRequest();

            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var json = JSON.parse(this.responseText);
                    alert("minDesbalance: "+json.minDesbalance);
                }
            };
            var path = "/requestBalance/generate.json?lat=_LAT&lng=_LNG&radius=_RADIUS"
                .replace("_LAT", cityCircle.center.lat)
                .replace("_LNG", cityCircle.center.lng)
                .replace("_RADIUS", cityCircle.radius);
            xhttp.open("GET", path, true);
            xhttp.send();
        }

        function loadHosp() {
            var xhttp = new XMLHttpRequest();

            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    var json = JSON.parse(this.responseText);
                    json.forEach(function (hospital) {
                        addMarker({
                            lat: parseFloat(hospital.latitude),
                            lng: parseFloat(hospital.longitude)
                        }, hospital.name)
                    });
                }
            };
            var path = "/hospital/index.json";
            xhttp.open("GET", path, true);
            xhttp.send();
        }

        // Adds a marker to the map and push to the array.
        function addMarker(location, title) {
            var marker = new google.maps.Marker({
                position: location,
                map: map
            });
            markers.push(marker);
        }

        // This example adds a user-editable rectangle to the map.
        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: chicago,
                zoom: 12
            });
            // Define a rectangle and set its editable property to true.
            cityCircle = new google.maps.Circle({
                center: chicago,
                radius: 12438.182480060008,
                editable: true,
                geodesic:false
            });
            cityCircle.setMap(map);

            // Create the DIV to hold the control and call the CenterControl()
            // constructor passing in this DIV.
            var centerControlDiv = document.createElement('div');
            var centerControl = new CenterControl(centerControlDiv, map);

            centerControlDiv.index = 1;
            map.controls[google.maps.ControlPosition.TOP_CENTER].push(centerControlDiv);
            loadHosp()
        }

    </script>
</head>

<body>
<div id="map"></div>
<script src="https://maps.googleapis.com/maps/api/js?key=${grailsApplication.config.google.key}&callback=initMap" async
        defer></script>
</body>
</html>
