package pl.wat.wcy.panek.saaggregator.adapter.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MqttReceiver {

    private final ObjectMapper objectMapper;
    private final IMqttClient client;
    private final MqttListener listener;
    private final String topic;

    public MqttReceiver(ObjectMapper objectMapper, IMqttClient client, MqttListener listener,
                        @Value("${application.mqtt.topic}") String topic) {
        this.objectMapper = objectMapper;
        this.client = client;
        this.listener = listener;
        this.topic = topic;
    }


    public void init() {
        try {
            client.subscribe(topic, (t, message) -> {
                listener.event(this.objectMapper.readValue(message.getPayload(), Map.class));
            });
        } catch (MqttException e) {
            log.error("Mqtt error: ", e);
        }
    }

}
