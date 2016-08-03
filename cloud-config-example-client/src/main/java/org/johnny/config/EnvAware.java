package org.johnny.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * A way to observe the Environment at startup.
 *
 * Created by johnny on 03/08/2016.
 */
@Configuration
public class EnvAware implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {

        this.environment = environment;
    }
}
