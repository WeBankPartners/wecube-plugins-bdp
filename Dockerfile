from platten/alpine-oracle-jre8-docker
LABEL maintainer = "Webank CTB Team"
ADD target/wecube-plugins-bdp-0.0.1-SNAPSHOT.jar /application/wecube-plugins-bdp.jar
ADD build/start.sh /scripts/start.sh
RUN chmod +x /scripts/start.sh
CMD ["/bin/sh","-c","/scripts/start.sh $OPS_SERVER_URL"]
