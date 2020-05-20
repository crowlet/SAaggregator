package pl.wat.wcy.panek.saaggregator.infrastructure.persistence.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbState {
    private String name;
    private LocalDateTime timestamp;
}
