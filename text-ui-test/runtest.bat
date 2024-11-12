@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

java -jar %jarloc% < ..\..\text-ui-test\input.txt > ..\..\text-ui-test\ACTUAL.TXT

cd ..\..\text-ui-test

FC ACTUAL.TXT EXPECTED.TXT
REM Check the result of the comparison
IF ERRORLEVEL 1 (
    echo ********** TEST FAILED **********
    echo Updating EXPECTED.TXT with the new ACTUAL.TXT content.
    echo.

    REM Replace EXPECTED.TXT with ACTUAL.TXT
    copy /y ACTUAL.TXT EXPECTED.TXT

    echo Please review the changes if necessary.
    exit /b 1
) ELSE (
    echo ********** TEST PASSED **********
)
