#! /bin/sh
SCRIPT_DIR="$(dirname "${0}")"
java -jar "$SCRIPT_DIR/${project.build.finalName}.jar"
