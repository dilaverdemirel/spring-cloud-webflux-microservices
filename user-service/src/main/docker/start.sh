#!/bin/sh

echo "**********************************************"
echo "------------Environment Variables------------"
echo "Active Spring Profile : $SPRING_PROFILE"
echo "Java Options : $JAVA_OPTS"
echo "**********************************************"

java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILE -jar app.jar