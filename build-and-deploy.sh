#!/bin/bash
# build and deploy to Tomcat manually 
mvn clean package
# NOTE: edit this to suite your deployment environment
export DEPLOYMENT_DIR=~/Apps/apache-tomcat-7/webapps
cp target/JSF_Validation_Demo.war $DEPLOYMENT_DIR  
