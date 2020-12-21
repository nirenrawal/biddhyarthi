package exam.solution.repository;

import exam.solution.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepo extends JpaRepository <Supervisor, Integer> {
}
