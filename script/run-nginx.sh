#!/bin/bash
docker rm -f nginx
docker run --name nginx \
        -v ~/dstagram-backend/docker/nginx/nginx.conf:/etc/nginx/nginx.conf:ro \
        -v ~/dstagram-frontend/build/:/dstagram/static \
        -p 80:80 \
        -d nginx