package com.richardson.dna.repository;

import com.richardson.dna.model.CadeiasDna;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DnaAnalyzerRepository extends MongoRepository<CadeiasDna, String> {
    @Query("{'dna': {$all : [?0] }}")
    List<CadeiasDna> findByCondition(List<String> cadeiaDna);
}
