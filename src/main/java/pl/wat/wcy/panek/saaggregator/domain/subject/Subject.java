package pl.wat.wcy.panek.saaggregator.domain.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private Sex sex;
    private Set<State> states;

    public void newState(StateValue value) {
        if(CollectionUtils.isEmpty(states)) {
            states = new HashSet<>();
        }
        states.add(new State(value, LocalDateTime.now()));
    }
}
