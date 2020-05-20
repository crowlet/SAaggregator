package pl.wat.wcy.panek.saaggregator.domain.subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectRepository {

    List<Subject> findAllSubjects();

    Optional<Subject> findById(UUID key);

    void save(Subject value);
}
