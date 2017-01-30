package com.waracle.cakemgr;

import com.waracle.cakemgr.service.CakeExternalDataSource;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.transaction.Transactional;

import static java.util.stream.StreamSupport.stream;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCaching
public class ApplicationContextConfig implements CommandLineRunner {

    @Value("${spring.application.external.datasource.gist}")
    private String gistUrl;
    @Value("${spring.application.external.datasource.loadData.enabled}")
    private boolean loadData;

    @Autowired
    private CakeExternalDataSource source;
    @Autowired
    private CakeService service;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationContextConfig.class).web(true).run(args);
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        if (loadData) {
            stream(source.loadFromGist(gistUrl).spliterator(), true).forEach(service::save);
        }
    }

}
