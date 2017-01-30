package com.waracle.cakemgr.api;

import com.waracle.cakemgr.service.CakeService;
import com.waracle.cakemgr.vo.CakeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.waracle.cakemgr.formater.CakeTransformer.toHumanReadable;
import static com.waracle.cakemgr.vo.CakeVO.builder;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@SuppressWarnings("unused")
public class CakeController {

    private final CakeService service;

    @Autowired
    public CakeController(CakeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = TEXT_PLAIN_VALUE)
    @ResponseBody
    public String loadCakesInHumanReadableFormat() {
        final StringBuilder cakes = new StringBuilder();
        service.findAll().forEach(cake -> cakes.append(toHumanReadable(cake)));
        return cakes.toString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = TEXT_PLAIN_VALUE)
    @ResponseStatus(CREATED)
    public void addCake(@RequestParam(value = "title") String title, @RequestParam(value = "desc") String description, @RequestParam(value = "image") String image) throws IOException {
        CakeVO cake = builder()
                .setTitle(title)
                .setDesc(description)
                .setImage(image)
                .build();
        service.save(cake);
    }

    @RequestMapping(value = "/cakes", method = RequestMethod.GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CakeVO> loadCakesInJsonFormat() {
        return service.findAll();
    }

    @RequestMapping(value = "/cakes", method = RequestMethod.GET, consumes = TEXT_PLAIN_VALUE, produces = TEXT_PLAIN_VALUE)
    @ResponseBody
    public String loadCakesInHumanReadable() {
        return loadCakesInHumanReadableFormat();
    }

    @RequestMapping(value = "/cakes", method = RequestMethod.POST)
    @ResponseStatus(CREATED)
    public void addCake(@RequestBody CakeVO cake) throws IOException {
        service.save(cake);
    }

}
