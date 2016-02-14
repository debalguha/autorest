package me.mindex.autorest.persistence.repo;

import java.util.Collection;

import me.mindex.autorest.model.YtmWordListEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface YtmWordListRepo extends JpaRepository<YtmWordListEntity, Long>, JpaSpecificationExecutor<YtmWordListEntity> {

	YtmWordListEntity findByWord(String word);
	@Query("from YtmWordListEntity where isComplete=:isComplete")
	Collection<YtmWordListEntity> scanCompleteStats(@Param("isComplete") Boolean isComplete);
}
