package me.mindex.autorest.service;

import java.util.Collection;

import me.mindex.autorest.model.YtmRegexTemplate;
import me.mindex.autorest.persistence.repo.YtmregexTemplateRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegexService {
	@Autowired
	private YtmregexTemplateRepo regexRepo;
	
	@Transactional(readOnly = true)
	public Collection<YtmRegexTemplate> findAllRegexTemplates(){
		return regexRepo.findAll();
	}
}
