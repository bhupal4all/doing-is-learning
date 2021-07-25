# Picocli - Command Line Interface Java Library

* [Official Documentation](https://picocli.info/)

## Create a Custom CLI Java Command for Todo Application

* Add Picocli library to `pom.xml`
    ```xml
    <dependency>
      <groupId>info.picocli</groupId>
      <artifactId>picocli</artifactId>
      <version>4.6.1</version>
    </dependency>
    ```
* Add Annotation Processor to `pom.xml`

    ```xml
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <!-- annotationProcessorPaths requires maven-compiler-plugin version 3.5 or higher -->
      <version>${maven-compiler-plugin-version}</version>
      <configuration>
        <annotationProcessorPaths>
          <path>
            <groupId>info.picocli</groupId>
            <artifactId>picocli-codegen</artifactId>
            <version>4.6.1</version>
          </path>
        </annotationProcessorPaths>
        <compilerArgs>
          <arg>-Aproject=${project.groupId}/${project.artifactId}</arg>
        </compilerArgs>
      </configuration>
    </plugin>
  ```

### Create a hello Command

* If user name is provided, print as `Welcome {User}` else `Welcome World`
* Create a Class by Implementing `java.util.Callable<Integer`
    * Generic Class Integer Refers to Command Exit Status
        * 0 - Success
* Annotate Class with `Command` and provide `name`, `header`, `description`, `version`
* Add `help` and `version`
    * Both options would use `boolean` instance variable
    * Add `Option` with `name`, `description`
        * For `help`, add `usageHelp` annotation property
        * For `version`, add `versionHelp` annotation property
* Create an instance variable for User
    * Annotate with `Option` and provide `name`, `description`, `defaultValue`
    