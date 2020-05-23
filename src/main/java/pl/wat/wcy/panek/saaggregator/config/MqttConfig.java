package pl.wat.wcy.panek.saaggregator.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MqttConfig {

    @Bean
    IMqttClient mqttClient(
            @Value("${application.mqtt.protocol}://${application.mqtt.host}:${application.mqtt.port}")
            final String mqttBrokerUrl,
            final PublisherIdProvider publisherIdProvider,
            final MqttConnectOptions options
    ) throws MqttException {

        var mqttClient = new MqttClient(mqttBrokerUrl, publisherIdProvider.id());
        log.info("Connecting to: " + mqttBrokerUrl);
        mqttClient.connect(options);
        return mqttClient;
    }

    @Bean
    MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        return options;
    }
}
