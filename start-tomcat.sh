#!/bin/sh

HERE=`pwd`
export JAVA_HOME=$HERE/jdk1.8.0_281
$HERE/apache-tomcat-7.0.62/bin/shutdown.sh
$HERE/apache-tomcat-7.0.62/bin/startup.sh
