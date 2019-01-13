FROM java:8
VOLUME /tmp

ADD *.jar /app.jar
ADD start.sh /start.sh

RUN sed -i 's/\r$//' /start.sh  && \
        chmod +x /start.sh

EXPOSE 5006
EXPOSE 6006
CMD ["sh","./start.sh"]