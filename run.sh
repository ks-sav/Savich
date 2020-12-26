#!/bin/bash

mvm compile
mvn package
mvn install
cd target | java -jar
