open module org.codefx.demo.jpms_hello_world {
	// main: copied from "main" module
	requires java.base;
	exports org.codefx.demo.jpms;

	// test: needed for modular testing
	requires org.junit.jupiter.api;
}
