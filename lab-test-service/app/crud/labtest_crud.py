import httpx
from sqlalchemy.orm import Session
from app.models.lab_test import LabTest
from app.models.test_order import TestOrder

USER_SERVICE_URL = "http://localhost:9000" 

async def validate_patient(patient_id: int) -> bool:
    async with httpx.AsyncClient() as client:
        response = await client.get(f"{USER_SERVICE_URL}/patients/{patient_id}")
        return response.status_code == 200

async def create_lab_test(db: Session, lab_test_data: dict):
    patient_exists = await validate_patient(lab_test_data['patient_id'])
    if not patient_exists:
        raise Exception("Patient not found in User Service.")

    new_test = LabTest(**lab_test_data)
    db.add(new_test)
    db.commit()
    db.refresh(new_test)
    return new_test

def get_lab_test_by_id(db: Session, test_id: int):
    return db.query(LabTest).filter(LabTest.test_id == test_id).first()

def get_lab_tests_by_patient(db: Session, patient_id: int):
    return db.query(LabTest).filter(LabTest.patient_id == patient_id).all()

def delete_lab_test_by_id(db: Session, test_id: int):
    test = db.query(LabTest).filter(LabTest.test_id == test_id).first()
    if test:
        db.delete(test)
        db.commit()
        return True
    return False
