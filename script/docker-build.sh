#!/bin/bash
cp application.yml ~/dstagram-backend/src/main/resources/application.yml
cd ~/dstagram-backend
./gradlew build -x test
cp build/libs/*.war docker/war/
cd docker
docker build -t dstagram .