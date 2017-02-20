package org.websandbox.learning.layout.actuators.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class HealthChecker implements HealthIndicator {
    @Override
    public Health health() {
        if (isApplicationHealthy()) {
        	return Health.up().build();
        }
        return Health.down().status(Status.OUT_OF_SERVICE).build();
    }
     
    public boolean isApplicationHealthy() {
        // Your logic to check health
    	// might include DB calls
        return false;
    }
}