@ECHO OFF

SET DIR=%~dp0
SET DEFAULT_JAR=%DIR%\gradle\wrapper\gradle-wrapper.jar

java -jar "%DEFAULT_JAR%" %*
