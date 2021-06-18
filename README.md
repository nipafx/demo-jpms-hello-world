# Hello, Modular World!

A simple Hello-World example for the Java module system.
Learn more about it [in my module system tutorial](https://nipafx.dev/java-module-system-tutorial/).

## Maven

To build and run with Maven execute these commands in the project's root:

```
mvn verify
java
	--module-path target/hello-modules-1.0-SNAPSHOT.jar
	--module dev.nipafx.demo.hello_modules/dev.nipafx.demo.modules.HelloModularWorld
```

## Command Line

To compile, package and launch without Maven on Linux:

```
javac
	-d target/classes
	$(find src -name '*.java')

jar --create
	--file target/hello-modules.jar
	--main-class dev.nipafx.demo.modules.HelloModularWorld
	-C target/classes .
java
	--module-path target/hello-modules.jar
	--module dev.nipafx.demo.hello_modules
```

On Windows you would have to explicitly enumerate the sources for the `javac` command, but otherwise it should be the same.

## Bach - JShell

You can use the `bach-build-run.jsh` JShell script (from [Bach](https://github.com/sormuras/bach)) to execute the commands above.

On Linux/Mac:

```
./bach-build-run.jsh
```

On Windows, with `%JDK%\bin` on your `PATH` (where `%JDK` is JDK 9 or later):

```
jshell bach-build-run.jsh
```
