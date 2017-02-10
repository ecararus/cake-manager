package com.eca.cakemgr.repository;

import com.eca.cakemgr.domain.Cake;
import org.springframework.data.repository.CrudRepository;


public interface CakeRepository extends CrudRepository<Cake, Long> {
}
