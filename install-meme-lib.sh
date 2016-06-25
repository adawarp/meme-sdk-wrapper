#!/bin/sh
mvn install:install-file -Dfile=./MemeLib.jar -DgroupId=com.jins_jp.meme -DartifactId=MemeLib -Dversion=1.1.0 -Dpackaging=jar
