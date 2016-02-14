package me.mindex.autorest.persistence.repo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import me.mindex.autorest.model.TblVideos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface TblVideoRepo extends JpaRepository<TblVideos, Long>, JpaSpecificationExecutor<TblVideos> {
	@Query("select year(videoUploadedDate), count(videoId) from TblVideos group by year(videoUploadedDate)")
	Map<Integer, Long> yearlyVideoCount();
	@Query("select videoCategory as videoCategory, videoUploadedDate as videoUploadedDate from TblVideos")
	List<Map<String, Date>> categoricalVideoUploadDates();
}
