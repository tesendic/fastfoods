package pmf.gis.fastfoods.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lu_franchises database table.
 * 
 */
@Entity
@Table(name="lu_franchises")
@NamedQuery(name="Franchise.findAll", query="SELECT f FROM Franchise f")
public class Franchise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="franchise_code")
	private String franchiseCode;

	@Column(name="franchise_name")
	private String franchiseName;

	//bi-directional many-to-one association to Fastfood
	@OneToMany(mappedBy="franchise")
	private List<Fastfood> fastfoods;

	public Franchise() {
	}

	public String getFranchiseCode() {
		return this.franchiseCode;
	}

	public void setFranchiseCode(String franchiseCode) {
		this.franchiseCode = franchiseCode;
	}

	public String getFranchiseName() {
		return this.franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public List<Fastfood> getFastfoods() {
		return this.fastfoods;
	}

	public void setFastfoods(List<Fastfood> fastfoods) {
		this.fastfoods = fastfoods;
	}

	public Fastfood addFastfood(Fastfood fastfood) {
		getFastfoods().add(fastfood);
		fastfood.setFranchise(this);

		return fastfood;
	}

	public Fastfood removeFastfood(Fastfood fastfood) {
		getFastfoods().remove(fastfood);
		fastfood.setFranchise(null);

		return fastfood;
	}

}