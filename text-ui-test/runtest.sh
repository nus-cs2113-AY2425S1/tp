#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT

echo "==== ACTUAL.TXT ===="
cat ACTUAL.TXT
echo "====================="

diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
