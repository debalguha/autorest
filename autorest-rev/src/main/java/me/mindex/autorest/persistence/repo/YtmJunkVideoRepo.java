package me.mindex.autorest.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.mindex.autorest.model.TblVideos;
import me.mindex.autorest.model.YtmJunkVideo;


public interface YtmJunkVideoRepo extends JpaRepository<YtmJunkVideo, Integer>, JpaSpecificationExecutor<TblVideos> {
}
