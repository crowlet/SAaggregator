package pl.wat.wcy.panek.saaggregator.domain.triage;

import java.util.Map;

public interface TriageDecisionMaker {

    Category decide(Map<String, TriageDataWrapper<Number>> data);
}
