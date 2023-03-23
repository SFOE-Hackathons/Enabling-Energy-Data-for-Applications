package ch.opendata.hack.energy.repository;


import ch.opendata.hack.energy.model.DummyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<DummyModel,Long> {



}
