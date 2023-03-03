FROM java:8
VOLUME /tmp
ADD /target/ClockBlog.jar ClockBlog.jar
EXPOSE 8888
ENTRYPOINT [ "java", "-jar", "/ClockBlog.jar" ]