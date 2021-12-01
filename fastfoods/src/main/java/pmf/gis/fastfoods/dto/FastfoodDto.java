package pmf.gis.fastfoods.dto;

import org.locationtech.jts.io.geojson.GeoJsonWriter;

import pmf.gis.fastfoods.model.Fastfood;
import pmf.gis.fastfoods.model.Franchise;

public class FastfoodDto {
	
	private Integer id;
	private FranchiseDto franchise;
	private String geoJson;
	
	
	public FastfoodDto(Integer id, FranchiseDto franchise, String geoJson) {
		this.id = id;
		this.franchise = franchise;
		this.geoJson = geoJson;
	}
	
	public FastfoodDto(Fastfood fastfood) {
		this.id = fastfood.getFfId();
		this.franchise = new FranchiseDto(fastfood.getFranchise());
		GeoJsonWriter geoJsonWriter = new GeoJsonWriter();
		this.geoJson = geoJsonWriter.write(fastfood.getGeom());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public FranchiseDto getFranchise() {
		return franchise;
	}
	public void setFranchise(FranchiseDto franchise) {
		this.franchise = franchise;
	}
	public String getGeoJson() {
		return geoJson;
	}
	public void setGeoJson(String geoJson) {
		this.geoJson = geoJson;
	}
	
	

}
