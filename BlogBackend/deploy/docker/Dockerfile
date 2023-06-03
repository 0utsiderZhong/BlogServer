# -----deploy by docker-----
#docker build -t xxx:xxx Dockerfile .
#docker run -it -n nginx-backend -p 9999:9002 <image name>/<image id> bash

# basic image
FROM nginx
# author
MAINTAINER clockZhong
# volume
VOLUME /apps/deploy/backend

RUN mkdir -p /apps/deploy/backend
WORKDIR /apps/deploy/backend

COPY ../maunal/nginx.conf /etc/nginx/nginx.conf
COPY ../../admin /apps/deploy/backend
