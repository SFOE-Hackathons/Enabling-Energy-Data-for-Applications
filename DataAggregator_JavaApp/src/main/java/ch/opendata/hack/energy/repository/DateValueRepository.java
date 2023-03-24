package ch.opendata.hack.energy.repository;


import ch.opendata.hack.energy.model.DateValue;
import ch.opendata.hack.energy.model.IntegerValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateValueRepository extends JpaRepository<DateValue,Long> {

}
