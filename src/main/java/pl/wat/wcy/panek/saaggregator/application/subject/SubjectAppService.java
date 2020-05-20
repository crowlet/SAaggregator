package pl.wat.wcy.panek.saaggregator.application.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wat.wcy.panek.saaggregator.domain.subject.StateValue;
import pl.wat.wcy.panek.saaggregator.domain.subject.Subject;
import pl.wat.wcy.panek.saaggregator.domain.subject.SubjectDomainService;
import pl.wat.wcy.panek.saaggregator.domain.triage.TriageDomainService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SubjectAppService {

    private final SubjectDomainService domainService;
    private final TriageDomainService triageDomainService;

    public List<Subject> all() {
        return domainService.allSubjects();
    }

    public void update(Map<String, Object> updates) {
        triageDomainService.determineAndGroupCategories(updates).entrySet().forEach(e -> {
            domainService.newState(e.getKey(), StateValue.valueOf(e.getValue().name()));
        });
    }
}
