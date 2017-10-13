//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

/*
 * Download "Bach.java" and "Bach.jsh" from github to local "target" directory.
 */
Path target = Files.createDirectories(Paths.get("target"))
URL context = new URL("https://raw.githubusercontent.com/sormuras/bach/master/src/bach/")
for (Path script : Set.of(target.resolve("Bach.java"), target.resolve("Bach.jsh"))) {
    if (Files.exists(script)) continue; // comment to force download files
    try (InputStream stream = new URL(context, script.getFileName().toString()).openStream()) {
        Files.copy(stream, script, StandardCopyOption.REPLACE_EXISTING);
    }
}

/*
 * Switch Bach to verbose mode. That'll print the commands before execution.
 */
System.setProperty("bach.verbose", "true")

/*
 * Source "Bach.java" and "Bach.jsh" into this jshell session.
 */
/open target/Bach.java
/open target/Bach.jsh

/*
 * Define global properties.
 */
String name = "org.codefx.demo.jpms_hello_world"
String main = "org.codefx.demo.jpms.HelloModularWorld"
Path classes = Paths.get("target/bach/classes")
Path sources = Paths.get("src/main/java")
Path archive = Paths.get("target/bach/jpms-hello-world.jar")

/*
 * Compile.
 *
 * Compile using "--module-source-path" and "--module" combo fails:
 * <pre>
 *   javac("-d", classes, "--module-source-path", sources, "--module", name)
 *   error: module org.codefx.demo.jpms_hello_world not found in module source path
 * </pre>
 * So, we have to simulate "$(find src -name '*.java')" using a self-expanding visitor.
 */
Predicate<Path> isJavaFile = path -> path.getFileName().toString().endsWith(".java")
Bach.Command.Visitor files = command -> command.addAll(sources, isJavaFile)
javac("-d", classes, files)

/*
 * Package.
 */
jar("--create", "--file", archive, "--main-class", main, "-C", classes, ".")

/*
 * Launch.
 */
java("--module-path", archive, "--module", name)

/exit
