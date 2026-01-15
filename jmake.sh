#!/bin/bash
mkdir -p bin
javac -d bin src/main/java/JavaPlatformer/*.java
java -cp bin JavaPlatformer.Main
