#!/bin/sh
mkdir -p /log
java -jar /application/wecube-plugins-bdp.jar  \
--server.address=0.0.0.0 \
--server.port=8081 \
--plugins.ops.url=$1 >>/log/wecube-plugins-bdp.log
