package nccucs;

import nccucs.domain.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Integer>  {

    List<Grade> findAll();

    Grade findById(int id);
}
