package com.mindex.autorest.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ChannelControllerTestWithHttp.class,
	VideoControllerTestWithHttp.class,
	WordListControllerTestWithHttp.class,
	MindexerControllerTestWithHttp.class
})
public class TestSuite {

}
