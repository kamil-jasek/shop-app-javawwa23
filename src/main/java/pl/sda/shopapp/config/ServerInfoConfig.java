package pl.sda.shopapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-20
 */
@Configuration
class ServerInfoConfig {

    private int serverPort;

    ServerInfoConfig(@Value("${server.port}") int serverPort) {
        this.serverPort = serverPort;
    }

    @Bean
    int serverPort() {
        return serverPort;
    }
}
