package pl.wat.wcy.panek.saaggregator.infrastructure.persistence.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "SUBJECT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSubject {

    @Id
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private String sex;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "STATE",
            joinColumns = {@JoinColumn(name = "SUBJECT_ID")}
    )
    private List<DbState> states;
}
