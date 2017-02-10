package com.eca.cakemgr.service;

import com.eca.cakemgr.vo.CakeVO;

public interface CakeExternalDataSource {

    Iterable<CakeVO> loadFromGist(String url);
}
