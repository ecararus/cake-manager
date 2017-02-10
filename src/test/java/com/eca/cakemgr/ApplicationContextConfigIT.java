package com.eca.cakemgr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationContextConfig.class)
@WebAppConfiguration
public class ApplicationContextConfigIT {

    @Test
    public void applicationShouldStart_withGivenConfiguration() {
        //test if app can start;
    }

}
