#GET /otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz HTTP/1.1

#FROM centos:centos7

# Prerequisites.

#RUN yum -y update
#RUN yum -y install wget tar

# Oracle Java 

#ENV	UPDATE_VERSION=8u181
#ENV	JAVA_VERSION=1.8.0_181
#ENV	BUILD=b13

#RUN	yum -y update && \
#	yum -y install wget && \
#	wget -c --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/${UPDATE_VERSION}-${BUILD}/jdk-${UPDATE_VERSION}-linux-x64.rpm" --output-document="jdk-${UPDATE_VERSION}-linux-x64.rpm" && \
#	rpm -i jdk-${UPDATE_VERSION}-linux-x64.rpm && \
#	alternatives --install /usr/bin/java java /usr/java/jdk${JAVA_VERSION}/bin/java 1 && \
#	alternatives --set java /usr/java/jdk${JAVA_VERSION}/bin/java && \
#	export JAVA_HOME=/usr/java/jdk${JAVA_VERSION}/ && \
#	echo "export JAVA_HOME=/usr/java/jdk${JAVA_VERSION}/" | tee /etc/environment && \
#	source /etc/environment && \
#	rm jdk-${UPDATE_VERSION}-linux-x64.rpm

#ENV	JAVA_HOME=/usr/java/jdk${JAVA_VERSION}/


FROM sgrio/java-oracle:jdk_8

# App

ENV WAR_FILE=springboot-angular.war

USER daemon

# This copies to local fat jar inside the image
ADD target/${WAR_FILE} /app/${WAR_FILE}

# What to run when the container starts
ENTRYPOINT [ "java", "-jar", "/app/${WAR_FILE}" ]

# Ports used by the app
EXPOSE 8080
