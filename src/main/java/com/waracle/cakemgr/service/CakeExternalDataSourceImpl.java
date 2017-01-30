package com.waracle.cakemgr.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.waracle.cakemgr.vo.CakeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.TEXT_PLAIN;

@Service
@SuppressWarnings("unused")
public class CakeExternalDataSourceImpl implements CakeExternalDataSource {

    private final Logger logger = LoggerFactory.getLogger(CakeExternalDataSourceImpl.class);

    private final ParameterizedTypeReference<List<CakeVO>> responseType = new ParameterizedTypeReference<List<CakeVO>>() {
    };

    @HystrixCommand(threadPoolKey = "CakeExternalDataSourceImpl", fallbackMethod = "findAllFailOverCallTransfer")
    @Override
    public Iterable<CakeVO> loadFromGist(String url) {
        return prepareRestTemplate()
                .exchange(url, GET, null, responseType)
                .getBody();
    }

    @SuppressWarnings("unused")
    public Iterable<CakeVO> findAllFailOverCallTransfer(String url, Throwable throwable) {
        logger.error("Failed fetching from: " + url, throwable);
        return of();
    }

    /**
     * Gist is producing MediaTypes.TEXT_PLAIN however the content is JSON,
     * In order to consume json and apply standard Converter, the MappingJackson2HttpMessageConverter is mutated to support TEXT_PLAIN.
     */
    private RestTemplate prepareRestTemplate() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(singletonList(TEXT_PLAIN));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

}
