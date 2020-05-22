package pl.wat.wcy.panek.saaggregator.domain.subject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static pl.wat.wcy.panek.saaggregator.domain.subject.StateValue.NO_DATA;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    private UUID id;
    private String name;
    private LocalDate birthDate;
    private Sex sex;
    @JsonIgnore
    private Set<State> states;

    @JsonProperty
    public StateValue getCurrentState() {
        return lastMeaningfulUpdate().map(State::getName).orElse(NO_DATA);
    }

    @JsonProperty
    LocalDateTime lastUpdateTimestamp() {
        return lastMeaningfulUpdate().map(State::getTimestamp).orElse(null);
    }

    private Optional<State> lastMeaningfulUpdate() {
        return states.stream().filter(s -> !NO_DATA.equals(s.getName())).max(Comparator.comparing(State::getTimestamp));
    }

    public void newState(StateValue value) {
        if(CollectionUtils.isEmpty(states)) {
            states = new HashSet<>();
        }
        states.add(new State(value, LocalDateTime.now()));
    }
}
