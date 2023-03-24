package ch.opendata.hack.energy.repository;


import ch.opendata.hack.energy.model.DoubleValue;
import ch.opendata.hack.energy.model.IntegerValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegerValueRepository extends JpaRepository<IntegerValue,Long> {

}
