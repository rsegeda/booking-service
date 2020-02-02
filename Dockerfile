FROM adoptopenjdk/openjdk13

ENV DEST_PATH=/opt/booking_service/

COPY build/install $DEST_PATH

WORKDIR $DEST_PATH

EXPOSE 8080

ENTRYPOINT ./booking-service-boot/bin/booking-service