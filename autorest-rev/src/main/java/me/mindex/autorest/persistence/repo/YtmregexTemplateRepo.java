package me.mindex.autorest.persistence.repo;

import me.mindex.autorest.model.YtmRegexTemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface YtmregexTemplateRepo extends JpaRepository<YtmRegexTemplate, Integer>, JpaSpecificationExecutor<YtmRegexTemplate> {

}
