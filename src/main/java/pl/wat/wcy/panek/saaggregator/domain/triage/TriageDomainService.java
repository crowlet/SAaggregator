package pl.wat.wcy.panek.saaggregator.domain.triage;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TriageDomainService {

    public Map<UUID, Category> determineAndGroupCategories(Map<String, Object> data) {
        var categories = Category.values();
        Set<UUID> users = (Set<UUID>) data.values()
                .stream()
                .map(Map.class::cast)
                .flatMap(e -> e.keySet().stream())
                .map(e -> UUID.fromString((String) e))
                .collect(Collectors.toSet());
        return users.stream().collect(Collectors.toMap(v -> v, v -> categories[new Random().nextInt(categories.length)]));
    }
}
