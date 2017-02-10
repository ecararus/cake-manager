package com.eca.cakemgr.service;

import com.eca.cakemgr.vo.CakeVO;


public interface CakeService {

    Iterable<CakeVO> findAll();

    void save(CakeVO cake);
}
