//package br.com.imd.pdse.monitorando.configuration;
//
//
//import javax.sql.DataSource;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Scope;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.support.TransactionTemplate;
//
//@Configuration
//@EnableJpaRepositories(basePackages = {"br.com.imd.pdse.monitorando"})
//@EnableTransactionManagement
//public class JpaConfiguration {
//
//    @Primary
//    @Bean("entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder,
//                                                                       @Qualifier("dataSource")
//                                                                       final DataSource dataSource){
//        return builder
//                .dataSource(dataSource)
//                .packages("br.com.imd.pdse.monitorando")
//                .build();
//    }
//
//    @Bean
//    @Primary
//    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") final EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//    @Bean
//    @Primary
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public TransactionTemplate transactionTemplate(@Qualifier("transactionManager") final PlatformTransactionManager transactionManager) {
//        return new TransactionTemplate(transactionManager);
//    }
//}
