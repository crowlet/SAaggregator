package pl.wat.wcy.panek.saaggregator.infrastructure.persistence.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.wat.wcy.panek.saaggregator.domain.subject.Subject;
import pl.wat.wcy.panek.saaggregator.domain.subject.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SubjectRepositoryImpl implements SubjectRepository {

    private final DbSubjectRepository repository;
    private final SubjectMapper mapper;

    @Override
    public List<Subject> findAllSubjects() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Subject> findById(UUID key) {
        return repository.findById(key).map(mapper::map);
    }

    @Override
    public void save(Subject value) {
        repository.save(mapper.map(value));
    }


}
