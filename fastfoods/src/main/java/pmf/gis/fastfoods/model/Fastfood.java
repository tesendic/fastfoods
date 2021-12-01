package pmf.gis.fastfoods.model;

import java.io.Serializable;
import javax.persistence.*;

import org.locationtech.jts.geom.Point;



/**
 * The persistent class for the fastfoods database table.
 * 
 */

@Entity
@Table(name="fastfoods")
@NamedQuery(name="Fastfood.findAll", query="SELECT f FROM Fastfood f")
public class Fastfood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ff_id")
	private Integer ffId;

	private Point geom;

	private double lat;

	private double lon;

	//bi-directional many-to-one association to Franchise
	@ManyToOne
	@JoinColumn(name="franchise")
	private Franchise franchise;

	public Fastfood() {
	}
	
	

	public Fastfood(Integer ffId, Point geom, double lat, double lon, Franchise franchise) {
		this.ffId = ffId;
		this.geom = geom;
		this.lat = lat;
		this.lon = lon;
		this.franchise = franchise;
	}


	public Integer getFfId() {
		return this.ffId;
	}

	public void setFfId(Integer ffId) {
		this.ffId = ffId;
	}

	public Point getGeom() {
		return this.geom;
	}

	public void setGeom(Point geom) {
		this.geom = geom;
	}

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return this.lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public Franchise getFranchise() {
		return this.franchise;
	}

	public void setFranchise(Franchise luFranchise) {
		this.franchise = luFranchise;
	}

}