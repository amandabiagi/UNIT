package amanda.biagi.unit.repositories;

import amanda.biagi.unit.models.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoUserRepository extends JpaRepository<InfoUser, Long> {

    InfoUser findByRa(Long ra);

}
