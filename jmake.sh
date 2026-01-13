#!/bin/bash

rm -rf bin
mkdir bin

javac -d bin -sourcepath src src/JavaPlatformer/*.java
java -cp bin JavaPlatformer.Main "$@"
