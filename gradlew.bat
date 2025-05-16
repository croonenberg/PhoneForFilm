@echo off
setlocal
set JAVA_HOME=%JAVA_HOME%
if not defined JAVA_HOME (
    echo JAVA_HOME is not set
    exit /b 1
)
"%JAVA_HOME%\bin\java.exe" -jar "%~dp0\gradle\wrapper\gradle-wrapper.jar" %*
