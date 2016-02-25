package me.mindex.autorest.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(YtmChannelsScan.class)
public class YtmChannelsScan_ {
	public static volatile SingularAttribute<YtmChannelsScan, Boolean> scanComplete;
	public static volatile SingularAttribute<YtmChannelsScan, Boolean> isWorking;
	public static volatile SingularAttribute<YtmChannelsScan, Integer> videosCount;
	public static volatile SingularAttribute<YtmChannelsScan, Date> lastUpdated;
}
