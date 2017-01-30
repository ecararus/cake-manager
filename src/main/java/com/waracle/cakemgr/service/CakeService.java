package com.waracle.cakemgr.service;

import com.waracle.cakemgr.vo.CakeVO;


public interface CakeService {

    Iterable<CakeVO> findAll();

    void save(CakeVO cake);
}
