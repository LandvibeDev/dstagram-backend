#!/bin/bash
input=$1
if [ $input = "-l" ]
then
    echo "local build"
else
    cp application.yml ~/dstagram-backend/src/main/resources/application.yml
fi
cd ~/dstagram-backend
./gradlew build -x test
cp build/libs/*.war docker/war/
cd docker
docker build -t dstagram .