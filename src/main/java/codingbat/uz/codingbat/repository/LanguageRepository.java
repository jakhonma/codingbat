package codingbat.uz.codingbat.repository;

import codingbat.uz.codingbat.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByName(String name);
}
