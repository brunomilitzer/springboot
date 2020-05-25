package br.com.brunomilitzer.trainings.springboot.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        final boolean error = true;

        if (error) {

            return Health.down().withDetail("Error Key", 123).build();
        }

        return Health.up().build();
    }
}
