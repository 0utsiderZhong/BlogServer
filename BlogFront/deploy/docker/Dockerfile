# -----deploy by docker-----
#docker build -t xxx:xxx Dockerfile .
#docker run -it -n nginx-front -p 9999:9001 <image name>/<image id> bash

# basic image
FROM nginx
# author
MAINTAINER clockZhong
# volume
VOLUME /apps/deploy/front

RUN mkdir -p /apps/deploy/front
WORKDIR /apps/deploy/front

COPY ../maunal/nginx.conf /etc/nginx/nginx.conf
COPY ../../dist /apps/deploy/front