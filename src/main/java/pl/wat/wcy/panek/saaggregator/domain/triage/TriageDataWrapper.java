package pl.wat.wcy.panek.saaggregator.domain.triage;

import lombok.Value;

@Value
public class TriageDataWrapper <T> {
    T value;

    static TriageDataWrapper<Object> empty() {
        return new TriageDataWrapper<>(null);
    }
}
