package pl.wat.wcy.panek.saaggregator.config;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CamundaConfig {

    @Value("${application.camunda.dmnFileResourcePath}")
    private String dmnFilePath;

    @Bean
    public List<DmnDecision> dmnModelInstance(DmnEngine engine) {
        final var dmnModelInstance = Dmn.readModelFromStream(getClass().getClassLoader().getResourceAsStream(dmnFilePath));
        return engine.parseDecisions(dmnModelInstance);
    }

    @Bean
    public DmnDecision triageDecision(List<DmnDecision> dmnDecisions) {
        return dmnDecisions.stream()
                .filter(d -> d.getKey().equals("triage"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Triage decision table not found"));
    }

    @Bean
    public DmnEngineConfiguration dmnEngineConfiguration() {
        return DmnEngineConfiguration.createDefaultDmnEngineConfiguration();
    }

    @Bean
    public DmnEngine dmnEngine(DmnEngineConfiguration configuration) {
        return configuration.buildEngine();
    }
}
