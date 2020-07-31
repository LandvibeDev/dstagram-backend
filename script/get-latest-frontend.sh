#!/bin/bash
rm -rf ~/dstagram-frontend
wget https://github.com/LandvibeDev/dstagram-frontend/releases/latest/download/out.zip \
&& unzip out.zip -d ~/dstagram-frontend
rm out.zip