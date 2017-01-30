package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.domain.Cake;
import org.springframework.data.repository.CrudRepository;


public interface CakeRepository extends CrudRepository<Cake, Long> {
}
