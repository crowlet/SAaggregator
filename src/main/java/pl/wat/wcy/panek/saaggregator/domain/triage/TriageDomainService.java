package pl.wat.wcy.panek.saaggregator.domain.triage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TriageDomainService {

    private final TriageDecisionMaker triageDecisionMaker;

    public Map<UUID, Category> determineAndGroupCategories(Map<String, Object> data) {
        var users = usersIds(data);
        return users.stream().collect(Collectors.toMap(v -> v, v -> decide(v, data)));
    }

    @SuppressWarnings("unchecked")
    private Category decide(UUID id, Map<String, Object> data) {
        Map<String, TriageDataWrapper<Number>> dataValues = (Map<String, TriageDataWrapper<Number>>) data.values()
                .stream()
                .flatMap(e -> ((Map) e).entrySet().stream())
                .collect(
                        Collectors.toMap(
                                e -> ((Map.Entry) e).getKey(),
                                e -> ((Map) ((Map.Entry) e).getValue()).entrySet()
                                        .stream()
                                        .filter(val -> UUID.fromString((String) ((Map.Entry) val).getKey()).equals(id))
                                        .findFirst()
                                        .map(val -> ((Map.Entry) val).getValue())
                                        .map(TriageDataWrapper::new)
                                        .orElse(TriageDataWrapper.empty())
                ));
        return triageDecisionMaker.decide(dataValues);
    }

    @SuppressWarnings("unchecked")
    private Set<UUID> usersIds(Map<String, Object> data) {
        return (Set<UUID>) data.values()
                .stream()
                .map(Map.class::cast)
                .flatMap(e -> e.values().stream())
                .flatMap(e -> ((Map) e).keySet().stream())
                .map(e -> UUID.fromString((String) e))
                .collect(Collectors.toSet());
    }
}
