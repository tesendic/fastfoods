package pmf.gis.fastfoods.repository;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pmf.gis.fastfoods.model.Fastfood;
import pmf.gis.fastfoods.model.Franchise;

public interface FastfoodRepository extends JpaRepository<Fastfood, Integer> {
	
	@Query("SELECT f FROM Fastfood f WHERE f.franchise = :franchise")
	Page<Fastfood> findByFranchise(Franchise franchise, Pageable page);
	
	@Query("SELECT f FROM Fastfood f WHERE f.franchise = :franchise")
	List<Fastfood> findByFranchise(Franchise franchise);
	
	@Query(value = "SELECT * FROM fastfoods f WHERE ST_DWithin(f.geom, ST_Transform(:point, 2163), 10000)", 
			nativeQuery = true)
	List<Fastfood> findByLocation(Point point);
	
	@Query(value = "SELECT * FROM Fastfoods f LIMIT :limit", nativeQuery = true)
	List<Fastfood> findAllLimit(Integer limit);
}
