package me.mindex.autorest.persistence.repo;

import me.mindex.autorest.model.YtmChannelVideo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface YtmChannelVideoRepo extends JpaRepository<YtmChannelVideo, Long>, JpaSpecificationExecutor<YtmChannelVideo> {

}
