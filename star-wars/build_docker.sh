#!/bin/sh
#  Process to deploy manually the docker image; from root folder, execute:
#       ./java-performance/build_Docker.sh

# Init variables
REGISTRY_NAME="java-performance"
MICROSERVICE_NAME="star-wars"
now="$(date +'%d-%m-%Y %H:%M:%S:%3N')"
base_dir=`pwd`
echo " REGISTRY_NAME="${REGISTRY_NAME}

./gradlew :star-wars:clean && ./gradlew :star-wars:build

cd star-wars
docker build -t=${REGISTRY_NAME}:${MICROSERVICE_NAME}-1.0-SNAPSHOT .

