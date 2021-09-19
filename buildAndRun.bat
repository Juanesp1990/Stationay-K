@echo off
call mvn clean package
call docker build -t com.mycompany/StationaryK .
call docker rm -f StationaryK
call docker run -d -p 9080:9080 -p 9443:9443 --name StationaryK com.mycompany/StationaryK