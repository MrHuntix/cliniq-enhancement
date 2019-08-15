# cliniq-enhancement
An enhancement of the already existing cliniq application

clone and import project and do maven clean, install.
To run

On IntelliJ

1 - Navigate to OcsApplication spring runner class and run(and cancel cause it wont render jsp pages).
2 - Select edit configurations and add a maven configuration by clicking the green + symbol.
3 - After clicking + in drop select maven option and add name(any thing eg: mvnRun) and commanfd line input with: spring-boot:run.
4 - save configuration and run app using this configuration.

Before doing anything do this first

Once application successfully launched go to http://localhost:8080/h2-console and click connect.

copy content from schema.sql and paste in the text area and click run which will create the tables.

Now proceed with the flow of the app http://localhost:8080/
