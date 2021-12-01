package pmf.gis.fastfoods.model;

import java.io.Serializable;
import javax.persistence.*;

import org.locationtech.jts.geom.MultiLineString;


/**
 * The persistent class for the roads database table.
 * 
 */
@Entity
@Table(name="roads")
@NamedQuery(name="Road.findAll", query="SELECT r FROM Road r")
public class Road implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer gid;

	private String feature;

	@Column(name="fnode_")
	private double fnode;

	private MultiLineString geom;

	private double length;

	@Column(name="lpoly_")
	private double lpoly;

	private String name;

	private double roadtrl020;

	@Column(name="rpoly_")
	private double rpoly;

	private String state;

	@Column(name="state_fips")
	private String stateFips;

	@Column(name="tnode_")
	private double tnode;

	public Road() {
	}

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getFeature() {
		return this.feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public double getFnode() {
		return this.fnode;
	}

	public void setFnode(double fnode) {
		this.fnode = fnode;
	}

	public MultiLineString getGeom() {
		return this.geom;
	}

	public void setGeom(MultiLineString geom) {
		this.geom = geom;
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getLpoly() {
		return this.lpoly;
	}

	public void setLpoly(double lpoly) {
		this.lpoly = lpoly;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRoadtrl020() {
		return this.roadtrl020;
	}

	public void setRoadtrl020(double roadtrl020) {
		this.roadtrl020 = roadtrl020;
	}

	public double getRpoly() {
		return this.rpoly;
	}

	public void setRpoly(double rpoly) {
		this.rpoly = rpoly;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateFips() {
		return this.stateFips;
	}

	public void setStateFips(String stateFips) {
		this.stateFips = stateFips;
	}

	public double getTnode() {
		return this.tnode;
	}

	public void setTnode(double tnode) {
		this.tnode = tnode;
	}

}