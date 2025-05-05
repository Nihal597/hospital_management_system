package com.hospitalmgmt.authentication.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hospitalmgmt.authentication.configuration.AppointmentServiceProperties;

@Service
public class AppointmentClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppointmentServiceProperties properties;

    public List<Map<String, Object>> getAppointmentsByDoctorId(int doctorId) {
        String url = properties.getHost() + properties.getDoctorAppointmentsPath() + doctorId;

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    public List<Map<String, Object>> getAppointmentsByPatientId(int patientId) {
        String url = properties.getHost() + properties.getPatientAppointmentsPath() + patientId;

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
