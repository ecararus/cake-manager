package com.waracle.cakemgr.service;

import com.waracle.cakemgr.vo.CakeVO;

public interface CakeExternalDataSource {

    Iterable<CakeVO> loadFromGist(String url);
}
