package pl.wat.wcy.panek.saaggregator.infrastructure.persistence.subject;

import org.springframework.stereotype.Component;
import pl.wat.wcy.panek.saaggregator.domain.subject.Sex;
import pl.wat.wcy.panek.saaggregator.domain.subject.State;
import pl.wat.wcy.panek.saaggregator.domain.subject.StateValue;
import pl.wat.wcy.panek.saaggregator.domain.subject.Subject;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SubjectMapper {

    DbSubject map(Subject val) {
        return new DbSubject(val.getId(), val.getName(), val.getBirthDate(), sex(val.getSex()), states(val.getStates()));
    }

    private List<DbState> states(Set<State> states) {
        return states.stream().map(this::map).collect(Collectors.toList());
    }

    private DbState map(State value) {
        return new DbState(value.getName().name(), value.getTimestamp());
    }

    private String sex(Sex sex) {
        return sex.name().substring(0, 1);
    }

    Subject map(DbSubject dbSubject) {
        return new Subject(
                dbSubject.getId(),
                dbSubject.getName(),
                dbSubject.getBirthDate(),
                sex(dbSubject.getSex()),
                states(dbSubject.getStates())
        );
    }

    private Set<State> states(Collection<DbState> states) {
        return states.stream().map(this::map).collect(Collectors.toSet());
    }

    private State map(DbState dbState) {
        return new State(StateValue.valueOf(dbState.getName()), dbState.getTimestamp());
    }

    private Sex sex(String sex) {
        return "F".equals(sex) ? Sex.FEMALE : Sex.MALE;
    }

}
