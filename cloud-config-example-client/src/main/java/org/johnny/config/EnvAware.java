package org.johnny.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * A way to observe the Environment at startup.
 * <p>
 * Created by johnny on 03/08/2016.
 */
@Configuration
public class EnvAware implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(final Environment environment) {

        this.env = environment;
    }
}
