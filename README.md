# Informatics 122 - Software Deisgn: Structure and Implementation

Contributors: Connor Darling, Gilberto Fernandez, Kenny Kim, Aung Myat, Vinny
Nguyen

## Installing Maven

macOS (Homebrew):
`brew install maven`

Windows:

1. Download the binary zip from `https://maven.apache.org/download.cgi`
2. Extract it somewhere (e.g., `C:\Program Files\Apache\maven`)
3. Add the bin folder to your `PATH` environment variable

Linux (apt):
`sudo apt install maven`

Verify with: `mvn --version`

## Maven Usage

`mvn compile`: compiles all source code
`mvn test`: runs unit tests
`mvn package`: compiles + tests + builds a JAR
`mvn clean`: deletes the target/ build directory
`mvn exec:java`: runs the main class directly

To run the built JAR after mvn package:
`java -jar target/guildquest-1.0.jar`

For dev: `mvn compile exec:java` is the fastest way to build and run
