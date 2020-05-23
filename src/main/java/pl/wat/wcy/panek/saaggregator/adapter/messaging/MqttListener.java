package pl.wat.wcy.panek.saaggregator.adapter.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wat.wcy.panek.saaggregator.application.subject.SubjectAppService;
import pl.wat.wcy.panek.saaggregator.domain.subject.SubjectDomainService;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqttListener {

    private final SubjectAppService subjectService;

    public void event(Map<String, Object> message) {
        log.info(message.toString());
        subjectService.update(message);
    }



}
