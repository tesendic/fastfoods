package pmf.gis.fastfoods.dto;

import pmf.gis.fastfoods.model.Franchise;

public class FranchiseDto {
	
	private String franchiseCode;
	private String franchiseName;
	
	
	public FranchiseDto(String franchiseCode, String franchiseName) {
		super();
		this.franchiseCode = franchiseCode;
		this.franchiseName = franchiseName;
	}
	
	public FranchiseDto(Franchise franchise) {
		this.franchiseCode = franchise.getFranchiseCode();
		this.franchiseName = franchise.getFranchiseName();
	}
	
	public String getFranchiseCode() {
		return franchiseCode;
	}
	public void setFranchiseCode(String franchiseCode) {
		this.franchiseCode = franchiseCode;
	}
	public String getFranchiseName() {
		return franchiseName;
	}
	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}
}
