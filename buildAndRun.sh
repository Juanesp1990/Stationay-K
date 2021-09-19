#!/bin/sh
mvn clean package && docker build -t com.mycompany/StationaryK .
docker rm -f StationaryK || true && docker run -d -p 9080:9080 -p 9443:9443 --name StationaryK com.mycompany/StationaryK