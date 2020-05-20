package pl.wat.wcy.panek.saaggregator.domain.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectDomainService {

    private final SubjectRepository repository;

    public List<Subject> allSubjects() {
        return repository.findAllSubjects();
    }
}
