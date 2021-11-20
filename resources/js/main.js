var vectorSource = new ol.source.Vector();

var main_layer = new ol.layer.Tile({source: new ol.source.OSM()})
var markers_layer =  new ol.layer.Vector({
    source: vectorSource
})

var map = new ol.Map({
    layers: [main_layer, markers_layer],
    target: 'map',
    view: new ol.View({
        center: [0, 0],
        zoom: 1
    })
});

function parsePoint(point_json) {
    return ol.proj.fromLonLat([point_json["latitude"], point_json["longitude"]])
}

function httpGetPoints()
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            json_points = JSON.parse(xmlHttp.responseText)
            var way = new ol.geom.LineString();

            for (i in json_points) {
                way.appendCoordinate(parsePoint(json_points[i]));
            }
            var last_point = json_points[json_points.length - 1];
            vectorSource.addFeature(
                new ol.Feature({
                    geometry: new ol.geom.Point(parsePoint(last_point))
                })
            )
            vectorSource.addFeature(
                new ol.Feature({
                    geometry: way,
                    style : new ol.style.Style({
                        stroke : new ol.style.Stroke({color : "rgb(255,0,0)"})})
                })
            )
        }
    }
    xmlHttp.open("GET", "/get_point", true);
    xmlHttp.send(null);
}

httpGetPoints();
