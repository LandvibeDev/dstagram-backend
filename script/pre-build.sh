#!/bin/bash
sudo yum install -y docker
sudo yum install -y java-11-amazon-corretto-headless

sudo systemctl start docker
sudo usermod -aG docker ec2-user

echo "restart shell"