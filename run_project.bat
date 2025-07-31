@echo off
echo Compiling source files...
mkdir out 2>nul
javac -cp "lib/*" -d out src\*.java

if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

echo Running Main class...
java -cp "lib/*;out" Main

pause