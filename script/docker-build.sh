#!/bin/bash
if [ -e application.yml ]
then
    cp application.yml ~/dstagram-backend/src/main/resources/application.yml
fi
cd ~/dstagram-backend
./gradlew build -x test
cp build/libs/*.war docker/war/
cd docker
docker build -t dstagram .