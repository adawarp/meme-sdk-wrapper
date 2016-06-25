#!/bin/sh
echo `java -version`
mvn  -Dexec.executable=java org.codehaus.mojo:exec-maven-plugin:1.2.1:exec "-Dexec.args=-noverify -classpath %classpath com.adawarp.meme.MemeSdk"
