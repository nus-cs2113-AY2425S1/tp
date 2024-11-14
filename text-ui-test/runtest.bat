@echo off

REM Ensure we're in the script's directory
cd /d "%~dp0"

REM Check if data directory and required files exist, create if necessary
if not exist "data" (
    echo data does not exist. Creating it now.......
    mkdir data
    echo. > data\carData.txt
    echo. > data\customerData.txt
    echo. > data\transactionData.txt
    echo data directory and files created successfully.
) else (
    echo. > data\carData.txt
    echo. > data\customerData.txt
    echo. > data\transactionData.txt
)

cd ..
call gradlew clean shadowJar

cd text-ui-test

REM Find the JAR file in the build/libs directory
for %%f in ("..\build\libs\*.jar") do set JAR_FILE=%%f

REM Check if the JAR file was found
if not defined JAR_FILE (
    echo Error: No jar file found in ..\build\libs
    echo Test failed!
    exit /b 1
)

REM Run the Java application and redirect input/output
java -jar "%JAR_FILE%" < input.txt > ACTUAL.TXT

REM Use PowerShell to convert line endings
powershell -Command "(Get-Content -Path 'EXPECTED.TXT') | Set-Content -NoNewline -Path 'EXPECTED-UNIX.TXT'"
powershell -Command "(Get-Content -Path 'ACTUAL.TXT') | Set-Content -NoNewline -Path 'ACTUAL.TXT'"

REM Compare the output files
fc /w EXPECTED-UNIX.TXT ACTUAL.TXT >nul
if %errorlevel% equ 0 (
    echo Test passed!
    exit /b 0
) else (
    echo Test failed!
    exit /b 1
)