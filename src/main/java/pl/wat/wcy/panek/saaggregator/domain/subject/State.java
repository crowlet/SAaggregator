package pl.wat.wcy.panek.saaggregator.domain.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {

    private StateValue name;
    private LocalDateTime timestamp;
}
