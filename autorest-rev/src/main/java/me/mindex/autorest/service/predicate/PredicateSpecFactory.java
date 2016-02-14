package me.mindex.autorest.service.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import me.mindex.autorest.model.YtmChannelVideo;
import me.mindex.autorest.model.YtmChannelVideo_;
import me.mindex.autorest.model.YtmChannelsScan;
import me.mindex.autorest.model.YtmChannelsScan_;
import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.model.YtmWordListEntity_;

import org.springframework.data.jpa.domain.Specification;

public class PredicateSpecFactory {
	public static Specification<YtmWordListEntity> buildAvailableWordSpec(){
		return new Specification<YtmWordListEntity>() {
			public Predicate toPredicate(Root<YtmWordListEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.<Boolean> get(YtmWordListEntity_.isComplete), Boolean.FALSE), 
						cb.equal(root.<Boolean> get(YtmWordListEntity_.isWorking), Boolean.FALSE));
			}
		};
	}
	
	public static Specification<YtmChannelsScan> buildAvailableChannelSpec(){
		return new Specification<YtmChannelsScan>() {
			public Predicate toPredicate(Root<YtmChannelsScan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.<Boolean> get(YtmChannelsScan_.scanComplete), Boolean.FALSE), 
						cb.equal(root.<Boolean> get(YtmChannelsScan_.isWorking), Boolean.FALSE));
			}
		};
	}
	
	public static Specification<YtmChannelsScan> buildLEVideoCountChannelSpec(int videoCount){
		return new Specification<YtmChannelsScan>() {
			public Predicate toPredicate(Root<YtmChannelsScan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.lessThanOrEqualTo(root.<Integer> get(YtmChannelsScan_.videosCount), videoCount);
			}
		};
	}
	
	public static Specification<YtmChannelsScan> buildGTVideoCountChannelSpec(int videoCount){
		return new Specification<YtmChannelsScan>() {
			public Predicate toPredicate(Root<YtmChannelsScan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThan(root.<Integer> get(YtmChannelsScan_.videosCount), videoCount);
			}
		};
	}
	
	public static Specification<YtmChannelVideo> buildVideoFromChannelSpec(YtmChannelsScan channel){
		return new Specification<YtmChannelVideo>() {
			public Predicate toPredicate(Root<YtmChannelVideo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.greaterThan(root.<YtmChannelsScan> get(YtmChannelVideo_.channel), channel);
			}
		};
	}
}
