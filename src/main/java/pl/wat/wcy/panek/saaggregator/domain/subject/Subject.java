package pl.wat.wcy.panek.saaggregator.domain.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
}
