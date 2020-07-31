#!/bin/bash
docker rm -f dstagram
docker run -d --name=dstagram -p 8080:8080  dstagram