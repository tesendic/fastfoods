package pmf.gis.fastfoods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pmf.gis.fastfoods.model.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, String> {

}
