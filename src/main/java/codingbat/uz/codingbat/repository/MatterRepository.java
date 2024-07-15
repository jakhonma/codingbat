package codingbat.uz.codingbat.repository;

import codingbat.uz.codingbat.entity.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatterRepository extends JpaRepository<Matter, Long> {
}
