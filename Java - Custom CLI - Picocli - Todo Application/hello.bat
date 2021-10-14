@echo off
mvn exec:java -Dexec.mainClass="com.learning.cli.picocli.command.HelloCommand" -Dexec.args=%*