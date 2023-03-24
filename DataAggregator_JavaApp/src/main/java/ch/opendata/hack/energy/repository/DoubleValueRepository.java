package ch.opendata.hack.energy.repository;


import ch.opendata.hack.energy.model.DoubleValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoubleValueRepository extends JpaRepository<DoubleValue,Long> {

}
