package com.example.midterm;

public class Diagnosis {
    private String patientID;
    private String symptoms;
    private String diagnosis;
    private String medicines;
    private boolean wardRequired;

    public Diagnosis(String patientID, String symptoms, String diagnosis, String medicines, boolean wardRequired) {
        this.patientID = patientID;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicines = medicines;
        this.wardRequired = wardRequired;
    }

    // Getters and setters
    public String getPatientID() { return patientID; }
    public void setPatientID(String patientID) { this.patientID = patientID; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getMedicines() { return medicines; }
    public void setMedicines(String medicines) { this.medicines = medicines; }

    public boolean isWardRequired() { return wardRequired; }
    public void setWardRequired(boolean wardRequired) { this.wardRequired = wardRequired; }
}
