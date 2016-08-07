package org.johnny.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Based on a short article about why @Value is bad (they get sprinkled everywhere).
 *
 * http://tuhrig.de/why-using-springs-value-annotation-is-bad/
 *
 * Using a service centralises the config, and allows hwo the config is obtained to be changed easily.
 *
 * Perhaps the class name is not the best, as it might be confused with the config server.
 *
 * Created by johnny on 07/08/2016.
 */
@Data
@Service
public class ConfigurationService {

    @Value("#{'${assignments}'.split(',')}")
    Set<String> assignments;
}
