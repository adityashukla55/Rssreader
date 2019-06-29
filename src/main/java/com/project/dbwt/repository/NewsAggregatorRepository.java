package com.project.dbwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.dbwt.model.NewsAggregatorModel;
import com.project.dbwt.model.*;

@Repository
public interface NewsAggregatorRepository extends JpaRepository<NewsAggregatorModel, Long> {
	
	

    @Modifying
    @Transactional
    @Query(value="delete  from NewsAggregatorModel c where c.link = ?1")
    void deleteByLink(String link);


    @Query("SELECT u FROM NewsAggregatorModel u WHERE u.title = ?1 AND u.link= ?2")
    List<NewsAggregatorModel> findByTitleAndLink(String title, String link);



}