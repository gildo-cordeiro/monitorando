//package br.com.imd.pdse.monitorando.configuration;
//
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
//@Configuration
//public class DataSourceConfiguration {
//
//    @Bean("writeConfiguration")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.write")
//    public HikariConfig writeConfiguration() {
//        return new HikariConfig();
//    }
//
//    @Bean("readConfiguration")
//    @ConfigurationProperties(prefix = "spring.datasource.hikari.read")
//    public HikariConfig readConfiguration() {
//        return new HikariConfig();
//    }
//
//    @Bean("writeDataSource")
//    public DataSource writeHikariDataSource() {
//        return new HikariDataSource(writeConfiguration());
//    }
//
//    @Bean("readDataSource")
//    public DataSource readHikariDataSource() {
//        return new HikariDataSource(readConfiguration());
//    }
//
//    @Primary
//    @Bean(name = "dataSource")
//    @DependsOn(value = {"writeDataSource", "readDataSource"})
//    DataSource dataSource(@Qualifier("readDataSource") final DataSource readDataSource,
//                          @Qualifier("writeDataSource") final DataSource writeDataSource) {
//        final var
//                targetDataSources =
//                Map.<Object, Object>of(RoutingDataSource.Route.WRITE,
//                        writeDataSource,
//                        RoutingDataSource.Route.READ,
//                        readDataSource);
//        final var routingDataSource = new RoutingDataSource();
//        routingDataSource.setTargetDataSources(targetDataSources);
//        routingDataSource.setDefaultTargetDataSource(writeDataSource);
//
//        return routingDataSource;
//    }
//}