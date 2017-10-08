# Hello, Modular World!

A simple Hello-World-example for the Java Platform Module System (JPMS).
Learn more about it [in my JPMS tutorial](https://blog.codefx.org/java/java-module-system-tutorial).

To build and run with Maven execute these commands in the project's root:

```
mvn clean install
java
	--module-path target/jpms-hello-world-1.0-SNAPSHOT.jar
	--module org.codefx.demo.jpms_hello_world/org.codefx.demo.jpms.HelloModularWorld
```

To compile, package and launch without Maven on Linux:

```
javac
	-d target/classes
	$(find src -name '*.java')
jar --create
	--file target/jpms-hello-world.jar
	--main-class org.codefx.demo.jpms.HelloModularWorld
	-C target/classes .
java
	--module-path target/jpms-hello-world.jar
	--module org.codefx.demo.jpms_hello_world
```

On Windows you would have to explicitly enumerate the sources for the `javac` command, but otherwise it should be the same.
