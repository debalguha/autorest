package me.mindex.autorest.persistence.repo;

import java.util.Collection;

import me.mindex.autorest.model.YtmChannelsScan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface YtmChannelsScanRepo extends JpaRepository<YtmChannelsScan, String>, JpaSpecificationExecutor<YtmChannelsScan> {
	@Query("from YtmChannelsScan where videosCount <= :videoCount")
	Collection<YtmChannelsScan> channelWithVideoCountLE(@Param("videoCount") int videoCount);
	@Query("from YtmChannelsScan where videosCount > :videoCount")
	Collection<YtmChannelsScan> channelWithVideoCountGT(@Param("videoCount") int videoCount);
	@Query("from YtmChannelsScan where scanComplete=:scanComplete")
	Collection<YtmChannelsScan> scanCompleteStats(@Param("scanComplete") Boolean scanComplete);
}
