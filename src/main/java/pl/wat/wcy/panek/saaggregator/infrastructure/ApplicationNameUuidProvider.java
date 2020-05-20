package pl.wat.wcy.panek.saaggregator.infrastructure;

import org.springframework.stereotype.Component;
import pl.wat.wcy.panek.saaggregator.SaaggregatorApplication;
import pl.wat.wcy.panek.saaggregator.config.PublisherIdProvider;

@Component
public class ApplicationNameUuidProvider implements PublisherIdProvider {

    @Override
    public String id() {
        return SaaggregatorApplication.class.getSimpleName();
    }
}
