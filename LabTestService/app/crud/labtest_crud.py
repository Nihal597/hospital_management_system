import httpx
from sqlalchemy.orm import Session
from app.models.lab_test import LabTest

USER_SERVICE_URL = "http://localhost:9000"
# USER_SERVICE_URL = "http://user-service:9000"
USERNAME = "anshul"
PASSWORD = "anshul@123"

# Get a JWT token
async def get_jwt_token() -> str:
    async with httpx.AsyncClient() as client:
        response = await client.post(
            f"{USER_SERVICE_URL}/api/users/generateToken",
            json={"username": USERNAME, "password": PASSWORD}
        )
        response.raise_for_status()
        return response.text 

# Call patient API with Bearer token
async def validate_patient(patient_id: int) -> bool:
    token = await get_jwt_token()
    headers = {"Authorization": f"Bearer {token}"}
    async with httpx.AsyncClient() as client:
        response = await client.get(f"{USER_SERVICE_URL}api/users/patients/{patient_id}", headers=headers)
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

def get_all_lab_tests(db: Session):
    return db.query(LabTest).all()

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

def update_lab_test_status(db: Session, test_id: int, new_status: str):
    test = db.query(LabTest).filter(LabTest.test_id == test_id).first()
    if not test:
        return None
    test.status = new_status
    db.commit()
    db.refresh(test)
    return test

