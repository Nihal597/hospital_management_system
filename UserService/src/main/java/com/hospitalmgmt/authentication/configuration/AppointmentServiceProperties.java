package com.hospitalmgmt.authentication.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "appointment.service")
public class AppointmentServiceProperties {

    private String host;
    private String doctorAppointmentsPath;
    private String patientAppointmentsPath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDoctorAppointmentsPath() {
        return doctorAppointmentsPath;
    }

    public void setDoctorAppointmentsPath(String doctorAppointmentsPath) {
        this.doctorAppointmentsPath = doctorAppointmentsPath;
    }

    /**
     * @return the patientAppointmentsPath
     */
    public String getPatientAppointmentsPath() {
        return patientAppointmentsPath;
    }

    /**
     * @param patientAppointmentsPath the patientAppointmentsPath to set
     */
    public void setPatientAppointmentsPath(String patientAppointmentsPath) {
        this.patientAppointmentsPath = patientAppointmentsPath;
    }

}
