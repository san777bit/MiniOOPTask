@echo off
set JAVA_HOME=%~dp0..\oracleJdk-27
set PATH=%JAVA_HOME%\bin;%PATH%

echo Compiling...
javac -d out Main.java models\*.java interfaces\*.java services\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Running...
echo.
java -cp out Main
pause
