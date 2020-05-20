package pl.wat.wcy.panek.saaggregator.infrastructure.persistence.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DbSubjectRepository extends JpaRepository<DbSubject, UUID> {
}
