package com.hospitalmgmt.appointment.dtos;

import java.sql.Date;
import java.sql.Time;

public class AppointmentDTO {
    private Integer doctorId;
    private Integer patientId;
    private Date appointmentDate;
    private Time appointmentTime;
    private String status;
    private String reason;

    /**
     * @return the doctorId
     */
    public Integer getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * @return the patientId
     */
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the appointmentDate
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the appointmentTime
     */
    public Time getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * @param appointmentTime the appointmentTime to set
     */
    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @param doctorId
     * @param patientId
     * @param appointmentDate
     * @param appointmentTime
     * @param status
     * @param reason
     */
    public AppointmentDTO(Integer doctorId, Integer patientId, Date appointmentDate, Time appointmentTime,
            String status, String reason) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
    }

    /**
     * 
     */
    public AppointmentDTO() {
    }

    @Override
    public String toString() {
        return "AppointmentDTO [doctorId=" + doctorId + ", patientId=" + patientId + ", appointmentDate="
                + appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status + ", reason=" + reason
                + "]";
    }

}
