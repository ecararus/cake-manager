package com.eca.cakemgr.service;


import com.eca.cakemgr.formater.CakeTransformer;
import com.eca.cakemgr.repository.CakeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.eca.cakemgr.domain.Cake;
import com.eca.cakemgr.vo.CakeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.google.common.collect.ImmutableList.of;

@Service
@Transactional
@SuppressWarnings("unused")
public class CakeServiceImpl implements CakeService {

    private final Logger logger = LoggerFactory.getLogger(CakeServiceImpl.class);

    private final CakeRepository repository;

    @Autowired
    public CakeServiceImpl(CakeRepository repository) {
        this.repository = repository;
    }

    @HystrixCommand(threadPoolKey = "CakeServiceImpl", fallbackMethod = "findAllFailOverCallTransfer")
    @Override
    public Iterable<CakeVO> findAll() {
        return CakeTransformer.toVos(repository.findAll());
    }

    @SuppressWarnings("unused")
    public Iterable<CakeVO> findAllFailOverCallTransfer(Throwable throwable) {
        logger.error("Persistence failed during findAll: ", throwable);
        return of();
    }

    @HystrixCommand(threadPoolKey = "CakeServiceImpl", fallbackMethod = "saveFailOverCallTransfer")
    @Override
    public void save(CakeVO cake) {
        Cake domain = CakeTransformer.toDomain(cake);
        repository.save(domain);
    }

    @SuppressWarnings("unused")
    public void saveFailOverCallTransfer(CakeVO cake, Throwable throwable) {
        throwable.printStackTrace();
        logger.error("Persistence failed during save of: " + cake, throwable);
    }

}


