package pl.wat.wcy.panek.saaggregator.domain.subject;

import java.util.List;

public interface SubjectRepository {

    List<Subject> findAllSubjects();
}
