#!/bin/sh

DIRNAME=`dirname $0`
RUNHOME=`cd $DIRNAME/; pwd`
echo @RUNHOME@ $RUNHOME

JAVA="$JAVA_HOME/bin/java"
JAVA_OPTS="-Xms50m -Xmx128m"
port=8777
JAVA_OPTS="$JAVA_OPTS -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$port,server=y,suspend=n"
Main_WAR=watchdog-server.war

echo ================== RUN_INFO  =============================================
echo @JAVA_HOME@ $JAVA_HOME
echo @JAVA@ $JAVA
echo @JAVA_OPTS@ $JAVA_OPTS
echo @Main_WAR@ $Main_WAR
echo ==========================================================================

echo start $APP_INFO ...
"$JAVA"  $JAVA_OPTS -jar "$Main_WAR"