package me.mindex.autorest.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.mindex.autorest.model.TblVideos;
import me.mindex.autorest.model.YtmChannelsScan;


public interface TblVideoRepo extends JpaRepository<TblVideos, Long>, JpaSpecificationExecutor<TblVideos> {
	@Query("select year(videoUploadedDate), count(videoId) from TblVideos group by year(videoUploadedDate)")
	List<Object[]> yearlyVideoCount();
	@Query("select videoCategory as videoCategory, videoUploadedDate as videoUploadedDate from TblVideos")
	List<Object[]> categoricalVideoUploadDates();
	@Query("select count(id) from TblVideos where channel = :channel")
	int videoCountForChannel(@Param("channel") YtmChannelsScan channel);
}
