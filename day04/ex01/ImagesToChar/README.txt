rm -rf target
mkdir target

curl -s https://repo1.maven.org/maven2/com/beust/jcommander/1.72/jcommander-1.72.jar -o lib/jcommander-1.72.jar
curl -s https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.0/JCDP-4.0.0.jar -o lib/JCDP-4.0.0.jar

javac -d target src/java/edu/school21/printer/logic/* src/java/edu/school21/printer/app/Program.java -cp "lib/JCDP-4.0.0.jar:lib/jcommander-1.72.jar"

cd target
jar xf ../lib/jcommander-1.72.jar
jar xf ../lib/JCDP-4.0.0.jar
cd ..

cp -r src/resources target

jar -cfm ./target/images-to-chars-printer.jar src/manifest.txt -C ./target .

java -jar ./target/images-to-chars-printer.jar --black=BLACK --white=WHITE