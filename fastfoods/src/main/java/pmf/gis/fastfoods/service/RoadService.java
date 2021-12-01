package pmf.gis.fastfoods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pmf.gis.fastfoods.model.Fastfood;
import pmf.gis.fastfoods.model.Road;
import pmf.gis.fastfoods.repository.RoadRepository;

@Service
public class RoadService {
	
	@Autowired
    RoadRepository roadRepository;
	
	public List<Road> getAllRoads(Optional<Integer> limit){
		if (limit.isPresent()) {
			Pageable page = PageRequest.of(0, limit.get());
			return roadRepository.findAll(page).getContent();
		} else {
			return roadRepository.findAll();
		}
    }
	
	public String getAllRoadsGeoJson(Optional<Integer> limit){
		List<Road> roads;
		if (limit.isPresent()) {
			Pageable page = PageRequest.of(0, limit.get());
			roads = roadRepository.findAll(page).getContent();
		} else {
			roads = roadRepository.findAll();
		}
        List<MultiLineString> lines = new ArrayList<MultiLineString>();
		for (Road road : roads) {
			lines.add(road.getGeom());
		}
		return convertToGeoJson(lines);
    }
	
	private String convertToGeoJson(List<MultiLineString> list) {
		if (list.isEmpty()) {
			return "{}";
		}
		try {
			GeometryFactory geometryFactory = new GeometryFactory(list.get(0).getPrecisionModel(), list.get(0).getSRID());
			GeometryCollection collection = geometryFactory.createGeometryCollection(list.toArray(new Geometry[] {}));
			
			CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:2163");
			MathTransform transform = CRS.findMathTransform(sourceCRS, DefaultGeographicCRS.WGS84, true);
			
			GeometryCollection targetCollection = (GeometryCollection) JTS.transform(collection, transform);
			targetCollection.setSRID(4326);
			GeoJsonWriter geoJsonWriter = new GeoJsonWriter();
			String geoJson = geoJsonWriter.write(targetCollection);
			return geoJson;
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
		
	}

}
