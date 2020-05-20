package pl.wat.wcy.panek.saaggregator.domain.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectDomainService {

    private final SubjectRepository repository;

    public List<Subject> allSubjects() {
        return repository.findAllSubjects();
    }

    public void newState(UUID key, StateValue newState) {
        var subject = repository.findById(key);
        subject.ifPresent(value -> {
            value.newState(newState);
            repository.save(value);
        });
    }
}
