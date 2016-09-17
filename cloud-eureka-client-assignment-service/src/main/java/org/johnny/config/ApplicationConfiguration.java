package org.johnny.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Based on a short article about why @Value is bad (they get sprinkled everywhere).
 * <p>
 * http://tuhrig.de/why-using-springs-value-annotation-is-bad/
 * <p>
 * Using a service centralises the config, and allows the way the config is obtained to be changed easily.
 * In this case, the data comes from the configuration service.
 * <p>
 * Created by johnny on 07/08/2016.
 */
@Data
@Service
public class ApplicationConfiguration {

    @Value("#{'${assignments}'.split(',')}")
    private Set<String> assignments;
}
