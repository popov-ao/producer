package edu.stateuniversityoftelecommunications.project.configuration;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Slf4j
public class DatabaseTestConfiguration {

    @Bean
    @ConditionalOnProperty(
            prefix = "test",
            value = "embedded.database",
            havingValue = "true",
            matchIfMissing = true)
    public DataSource dataSource() throws IOException {
        EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.builder().start();

        log.info("EmbeddedPostgres databased started, port: {}", embeddedPostgres.getPort());
        return embeddedPostgres.getPostgresDatabase();
    }
}
