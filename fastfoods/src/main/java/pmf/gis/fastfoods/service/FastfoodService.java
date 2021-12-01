package pmf.gis.fastfoods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pmf.gis.fastfoods.dto.Location;
import pmf.gis.fastfoods.model.Fastfood;
import pmf.gis.fastfoods.model.Franchise;
import pmf.gis.fastfoods.repository.FastfoodRepository;
import pmf.gis.fastfoods.repository.FranchiseRepository;

@Service
public class FastfoodService {
	
	@Autowired
    FastfoodRepository fastfoodRepository;
	
	@Autowired
    FranchiseRepository franchiseRepository;
	
	public List<Fastfood> getAllFastfoods(Optional<Integer> limit){
		if (limit.isPresent()) {
			Pageable page = PageRequest.of(0, limit.get());
			return fastfoodRepository.findAll(page).getContent();
		} else {
			return fastfoodRepository.findAll();
		}
    }
	
	public String getAllFastfoodsGeoJson(Optional<Integer> limit){
		List<Fastfood> fastfoods;
		if (limit.isPresent()) {
			Pageable page = PageRequest.of(0, limit.get());
			fastfoods = fastfoodRepository.findAll(page).getContent();
		} else {
			fastfoods = fastfoodRepository.findAll();
		}
        List<Point> points = new ArrayList<Point>();
		for (Fastfood fastfood : fastfoods) {
			points.add(fastfood.getGeom());
		}
		return convertToGeoJson(points);
    }
	
	public List<Fastfood> getFastfoodsByFranchise(String code, Optional<Integer> limit){
		Optional<Franchise> franchiseOptional = franchiseRepository.findById(code);
		if (franchiseOptional.isPresent()) {
			if (limit.isPresent()) {
				Pageable page = PageRequest.of(0, limit.get());
				return fastfoodRepository.findByFranchise(franchiseOptional.get(), page).getContent();
			} else {
				return fastfoodRepository.findByFranchise(franchiseOptional.get());
			}
		} else {
			return new ArrayList<Fastfood>();
		}
    }
	
	public String getFastfoodsByFranchiseGeoJson(String code, Optional<Integer> limit){
		Optional<Franchise> franchiseOptional = franchiseRepository.findById(code);
		if (franchiseOptional.isPresent()) {
			List<Fastfood> fastfoods;
			if (limit.isPresent()) {
				Pageable page = PageRequest.of(0, limit.get());
				fastfoods = fastfoodRepository.findByFranchise(franchiseOptional.get(), page).getContent();
			} else {
				fastfoods = fastfoodRepository.findByFranchise(franchiseOptional.get());
			}
			List<Point> points = new ArrayList<Point>();
			for (Fastfood fastfood : fastfoods) {
				points.add(fastfood.getGeom());
			}
			return convertToGeoJson(points);
		} else {
			return "{}";
		}
    }
	
	public List<Fastfood> getFastfoodsByLocation(Location location){
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
		Point point = geometryFactory.createPoint(new Coordinate(location.getLongitude(), location.getLatitude()));
		return fastfoodRepository.findByLocation(point);
		
    }
	
	public String getFastfoodsByLocationGeoJson(Location location){
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
		Point point = geometryFactory.createPoint(new Coordinate(location.getLongitude(), location.getLatitude()));
		List<Fastfood> fastfoods =  fastfoodRepository.findByLocation(point);
		List<Point> points = new ArrayList<Point>();
		for (Fastfood fastfood : fastfoods) {
			points.add(fastfood.getGeom());
		}
		return convertToGeoJson(points);
    }
	
	private String convertToGeoJson(List<Point> list) {
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
