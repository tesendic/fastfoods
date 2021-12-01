package pmf.gis.fastfoods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pmf.gis.fastfoods.dto.FastfoodDto;
import pmf.gis.fastfoods.dto.Location;
import pmf.gis.fastfoods.model.Fastfood;
import pmf.gis.fastfoods.service.FastfoodService;

@RestController
@RequestMapping("/fastfoods")
public class FastfoodController {
	
	@Autowired
    FastfoodService fastfoodService;
	
	@GetMapping("/getAll")
    public ResponseEntity<?> getAllFastfoods(@RequestParam Optional<Integer> limit) {
		try {
			List<Fastfood> fastfoods = fastfoodService.getAllFastfoods(limit);
			List<FastfoodDto> fastfoodsdto = new ArrayList<FastfoodDto>();
			for (Fastfood fastfood : fastfoods) {
				fastfoodsdto.add(new FastfoodDto(fastfood));
			}
			return ResponseEntity.ok(fastfoodsdto);
		} catch (Exception e){
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading fastfoods");
        }
    }
	
	@GetMapping("/getGeoJson")
    public ResponseEntity<?> getGeoJson(@RequestParam Optional<Integer> limit) {
		try {
			String geojson = fastfoodService.getAllFastfoodsGeoJson(limit);
			return ResponseEntity.ok(geojson);
		} catch (Exception e){
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading fastfoods");
        }
    }
	
	@GetMapping("/getByFrachise/{code}")
    public ResponseEntity<?> getByFranchise(@PathVariable("code") String code, @RequestParam Optional<Integer> limit) {
		if (code == null || code.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code cannot be empty");
		}
		try {
			List<Fastfood> fastfoods = fastfoodService.getFastfoodsByFranchise(code, limit);
			List<FastfoodDto> fastfoodsdto = new ArrayList<FastfoodDto>();
			for (Fastfood fastfood : fastfoods) {
				fastfoodsdto.add(new FastfoodDto(fastfood));
			}
			return ResponseEntity.ok(fastfoodsdto);
		} catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading fastfoods");
        }
    }
	
	@GetMapping("/getByFrachiseGeoJson/{code}")
    public ResponseEntity<?> getByFranchiseGeoJson(@PathVariable("code") String code, @RequestParam Optional<Integer> limit) {
		if (code == null || code.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code cannot be empty");
		}
		try {
			String geojson = fastfoodService.getFastfoodsByFranchiseGeoJson(code, limit);
			return ResponseEntity.ok(geojson);
		} catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading fastfoods");
        }
    }
	
	@PostMapping("/getByLocation")
    public ResponseEntity<?> getByLocation(@RequestBody Location location) {
		if (location == null || location.getLatitude() == null || location.getLongitude() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location cannot be empty");
		}
		try {
			List<Fastfood> fastfoods = fastfoodService.getFastfoodsByLocation(location);
			List<FastfoodDto> fastfoodsdto = new ArrayList<FastfoodDto>();
			for (Fastfood fastfood : fastfoods) {
				fastfoodsdto.add(new FastfoodDto(fastfood));
			}
			return ResponseEntity.ok(fastfoodsdto);
		} catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading fastfoods");
        }
    }
	
	@PostMapping("/getByLocationGeoJson")
    public ResponseEntity<?> getByLocationGeoJson(@RequestBody Location location) {
		if (location == null || location.getLatitude() == null || location.getLongitude() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location cannot be empty");
		}
		try {
			String geojson = fastfoodService.getFastfoodsByLocationGeoJson(location);
			return ResponseEntity.ok(geojson);
		} catch (Exception e){
			e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading fastfoods");
        }
    }

}
