package me.mindex.autorest.persistence.repo;

import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.model.YtmChannelsScan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface YtmChannelVideoRepo extends JpaRepository<YtmChannelVideo, Long>, JpaSpecificationExecutor<YtmChannelVideo> {
	@Query("select count(id) from YtmChannelVideo where channel = :channel")
	int videoCountForChannel(@Param("channel") YtmChannelsScan channel);
}
