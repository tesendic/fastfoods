package pmf.gis.fastfoods.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pmf.gis.fastfoods.model.Road;
import pmf.gis.fastfoods.service.RoadService;

@RestController
@RequestMapping("/roads")
public class RoadController {
	
	@Autowired
    RoadService roadService;
	
	@GetMapping("/getAll")
    public ResponseEntity<?> getAllRoads(@RequestParam Optional<Integer> limit) {
		try {
			List<Road> roads = roadService.getAllRoads(limit);
			return ResponseEntity.ok(roads);
		} catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading roads");
        }
    }

	@GetMapping("/getGeoJson")
    public ResponseEntity<?> getGeoJson(@RequestParam Optional<Integer> limit) {
		try {
			String geojson = roadService.getAllRoadsGeoJson(limit);
			return ResponseEntity.ok(geojson);
		} catch (Exception e){
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading roads");
        }
    }
}
