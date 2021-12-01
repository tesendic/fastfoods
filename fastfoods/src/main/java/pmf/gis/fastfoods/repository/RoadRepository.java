package pmf.gis.fastfoods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pmf.gis.fastfoods.model.Road;

public interface RoadRepository extends JpaRepository<Road, Integer> {
	
}
