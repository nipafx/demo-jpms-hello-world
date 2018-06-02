//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

/*
 * Download "Bach.java" and "Bach.jsh" from github to local "target" directory.
 */
Path target = Files.createDirectories(Paths.get("target"))
URL context = new URL("https://raw.githubusercontent.com/sormuras/bach/1.0.0/src/bach/")
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
 * Launch JUnit Platform Console.
 */
Bach.JdkTool.Java command = new Bach.JdkTool.Java()
command.modulePath = List.of(Paths.get("target/test-classes"), Paths.get("target/lib"))
command.addModules = List.of("ALL-MODULE-PATH,ALL-DEFAULT")
command.module = "org.junit.platform.console"
command.args = List.of("--scan-modules")
command.run()

/exit
