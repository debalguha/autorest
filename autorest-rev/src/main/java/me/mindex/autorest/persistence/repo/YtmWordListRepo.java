package me.mindex.autorest.persistence.repo;

import me.mindex.autorest.model.YtmWordListEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface YtmWordListRepo extends JpaRepository<YtmWordListEntity, Integer>, JpaSpecificationExecutor<YtmWordListEntity> {

	YtmWordListEntity findByWord(String word);
}
