/*
    Created by Roman Segeda on 02 February 2020
*/

package com.rsegeda.bookingservice.service.repository;

import com.rsegeda.bookingservice.service.model.Appointment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

}
