package org.codefx.demo.jpms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HelloModularWorldTests {

	@Test
	void test() {
		String actual = HelloModularWorld.class.getModule().getName();
		assertEquals("org.codefx.demo.jpms_hello_world", actual);
	}
}
