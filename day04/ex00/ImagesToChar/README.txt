rm -rf target

mkdir target

javac -d target src/java/edu.school21.printer/logic/PrintImage.java src/java/edu.school21.printer/app/Program.java

java -classpath ./target edu.school21.printer.app.Program --black=. --white=0 --path=/Users/arsondor/Downloads/it.bmp
