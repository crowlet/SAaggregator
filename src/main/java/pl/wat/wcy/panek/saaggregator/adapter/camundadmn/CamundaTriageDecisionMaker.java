package pl.wat.wcy.panek.saaggregator.adapter.camundadmn;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;
import pl.wat.wcy.panek.saaggregator.domain.triage.Category;
import pl.wat.wcy.panek.saaggregator.domain.triage.TriageDataWrapper;
import pl.wat.wcy.panek.saaggregator.domain.triage.TriageDecisionMaker;

import java.util.Map;
import java.util.Optional;

import static pl.wat.wcy.panek.saaggregator.domain.triage.Category.BLACK;

@Component
@RequiredArgsConstructor
public class CamundaTriageDecisionMaker implements TriageDecisionMaker {

    private final DmnDecision triageDecision;
    private final DmnEngine engine;

    @Override
    public Category decide(Map<String, TriageDataWrapper<Number>> data) {
        VariableMap variables = Variables.createVariables()
                .putValue("ecg", data.get("ecg_mean").getValue())
                .putValue("spo2", data.get("spo2_95p").getValue());
        return Optional.ofNullable(
                engine.evaluateDecision(triageDecision, variables)
                        .getFirstResult()
                        .getFirstEntry()
        )
                .map(String.class::cast)
                .map(Category::valueOf)
                .orElse(BLACK);
    }
}
