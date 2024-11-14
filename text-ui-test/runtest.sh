# Ensure we're in the script's directory
cd "${0%/*}"

# Check if data directory and required files exist, create if necessary
if [ ! -d "data" ]; then
    echo "data does not exist. Creating it now......."
    mkdir -p data
    touch data/carData.txt
    touch data/customerData.txt
    touch data/transactionData.txt
    echo "data directory and files created successfully."
fi
> data/carData.txt
> data/customerData.txt
> data/transactionData.txt

cd ..
./gradlew clean shadowJar

cd text-ui-test

java -jar $(find ../build/libs/ -name '*.jar' -print -quit) < input.txt > ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]; then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi