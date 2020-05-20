package pl.wat.wcy.panek.saaggregator.adapter.messaging;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MqttListener {

    public void event(Map<String, Object> message) {
        System.out.println(message);
    }



}
