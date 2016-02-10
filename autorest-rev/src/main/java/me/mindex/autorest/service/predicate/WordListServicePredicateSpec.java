package me.mindex.autorest.service.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.model.YtmWordListEntity_;

import org.springframework.data.jpa.domain.Specification;

public class WordListServicePredicateSpec {
	public static Specification<YtmWordListEntity> buildAvailableWordSpec(){
		return new Specification<YtmWordListEntity>() {
			public Predicate toPredicate(Root<YtmWordListEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.<Boolean> get(YtmWordListEntity_.isComplete), Boolean.FALSE), 
						cb.equal(root.<Boolean> get(YtmWordListEntity_.isWorking), Boolean.FALSE));
			}
		};
	}
}
