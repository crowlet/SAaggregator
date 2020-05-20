package pl.wat.wcy.panek.saaggregator.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wat.wcy.panek.saaggregator.application.subject.SubjectAppService;
import pl.wat.wcy.panek.saaggregator.domain.subject.SubjectDomainService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MqttListener {

    private final SubjectAppService subjectService;

    public void event(Map<String, Object> message) {
        subjectService.update(message);
    }



}
