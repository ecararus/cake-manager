package com.waracle.cakemgr.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.ApplicationContextConfig;
import com.waracle.cakemgr.domain.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static com.waracle.cakemgr.formater.CakeTransformer.toHumanReadable;
import static com.waracle.cakemgr.test.data.CakeMatcher.matchesWith;
import static com.waracle.cakemgr.test.data.CakeMother.produceCake;
import static com.waracle.cakemgr.test.data.CakeMother.produceCakeVO;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationContextConfig.class)
@WebAppConfiguration
@SuppressWarnings("unused")
public class CakeControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CakeRepository repository;

    @Before
    public void setup() throws Exception {
        repository.deleteAll();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void loadAllCakes() throws Exception {
        prepareTestData();
        MvcResult result = this.mockMvc.perform(get("/"))
                .andExpect(status().isOk()).andReturn();
        String expectedCake = toHumanReadable(produceCakeVO());
        String actualCake = result.getResponse().getContentAsString();
        assertThat(actualCake, equalTo(expectedCake));
    }

    @Test
    public void loadAllCakes_When_Repository_Is_Empty() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/"))
                .andExpect(status().isOk()).andReturn();
        String actualCake = result.getResponse().getContentAsString();
        assertThat(actualCake, isEmptyString());
    }

    @Test
    public void addCake() throws Exception {
        Cake cake = produceCake();
        this.mockMvc.perform(post("/add")
                .contentType(TEXT_PLAIN_VALUE)
                .param("title", cake.getTitle())
                .param("desc", cake.getDesc())
                .param("image", cake.getImage()))
                .andExpect(status().isCreated());
        assertThat(repository.findAll(), prepareMatcherForTestData());
    }

    @Test
    public void addDuplicatedCake() throws Exception {
        prepareTestData();
        Cake cake = produceCake();
        this.mockMvc.perform(post("/add")
                .contentType(TEXT_PLAIN_VALUE)
                .param("title", cake.getTitle())
                .param("desc", cake.getDesc())
                .param("image", cake.getImage()))
                .andExpect(status().is5xxServerError());
        assertThat(repository.findAll(), prepareMatcherForTestData());
    }

    @Test
    public void addCake_With_Wrong_Param() throws Exception {
        Cake cake = produceCake();
        this.mockMvc.perform(post("/add")
                .contentType(TEXT_PLAIN_VALUE)
                .param("title", cake.getTitle())
                .param("image", cake.getImage()))
                .andExpect(status().is4xxClientError());
        assertThat(repository.findAll(), emptyIterable());
    }

    @Test
    public void loadCakesInJsonFormat() throws Exception {
        prepareTestData();
        MvcResult result = this.mockMvc.perform(get("/cakes").contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String expectedCake = "[" + jsonSerializer(produceCakeVO()) + "]";
        String actualCake = result.getResponse().getContentAsString();
        assertThat(actualCake, equalTo(expectedCake));
    }

    @Test
    public void loadCakesInJsonFormat_When_Repository_Is_Empty() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/cakes").contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String actualCake = result.getResponse().getContentAsString();
        assertThat(actualCake, equalTo("[]"));
    }

    @Test
    public void loadCakesInHumanReadable() throws Exception {
        prepareTestData();
        MvcResult result = this.mockMvc.perform(get("/cakes").contentType(TEXT_PLAIN_VALUE))
                .andExpect(status().isOk()).andReturn();
        String expectedCake = toHumanReadable(produceCakeVO());
        String actualCake = result.getResponse().getContentAsString();
        assertThat(actualCake, equalTo(expectedCake));
    }

    @Test
    public void loadCakesInHumanReadable_When_Repository_Is_Empty() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/cakes").contentType(TEXT_PLAIN_VALUE))
                .andExpect(status().isOk()).andReturn();
        String actualCake = result.getResponse().getContentAsString();
        assertThat(actualCake, isEmptyString());
    }

    @Test
    public void addCake_As_Json_Content() throws Exception {
        Cake cake = produceCake();
        this.mockMvc.perform(post("/cakes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonSerializer(cake)))
                .andExpect(status().isCreated());
        assertThat(repository.findAll(), prepareMatcherForTestData());
    }

    @Test
    public void addDuplicatedCake_As_Json_Content() throws Exception {
        prepareTestData();
        Cake cake = produceCake();
        this.mockMvc.perform(post("/cakes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonSerializer(cake)))
                .andExpect(status().is5xxServerError());
        assertThat(repository.findAll(), prepareMatcherForTestData());
    }

    private void prepareTestData() {
        repository.save(produceCake());
    }

    private Matcher prepareMatcherForTestData() {
        return contains(matchesWith(produceCake()));
    }

    private String jsonSerializer(Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(value);
    }

}