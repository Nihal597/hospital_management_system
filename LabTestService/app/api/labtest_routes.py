from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from app.database import SessionLocal
from app.crud import labtest_crud

router = APIRouter()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.post("/labtests")
async def create_lab_test(lab_test: dict, db: Session = Depends(get_db)):
    try:
        new_test = await labtest_crud.create_lab_test(db, lab_test)
        return {"message": "Lab Test Created", "data": {
            "test_id": new_test.test_id,
            "test_name": new_test.test_name,
            "status": new_test.status
        }}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

@router.get("/labtests")
def get_all_lab_tests(db: Session = Depends(get_db)):
    tests = labtest_crud.get_all_lab_tests(db)
    return tests

@router.get("/labtests{test_id}")
def get_lab_test(test_id: int, db: Session = Depends(get_db)):
    test = labtest_crud.get_lab_test_by_id(db, test_id)
    if not test:
        raise HTTPException(status_code=404, detail="Lab Test Not Found")
    return test

@router.get("/labtests/patient/{patient_id}")
def get_patient_lab_tests(patient_id: int, db: Session = Depends(get_db)):
    tests = labtest_crud.get_lab_tests_by_patient(db, patient_id)
    return tests

@router.delete("/labtests/{test_id}")
def delete_lab_test(test_id: int, db: Session = Depends(get_db)):
    success = labtest_crud.delete_lab_test_by_id(db, test_id)
    if not success:
        raise HTTPException(status_code=404, detail="Lab Test Not Found")
    return {"message": f"Lab Test {test_id} deleted successfully"}

@router.put("/labtests/{test_id}/status")
def update_lab_test_status(test_id: int, status: str, db: Session = Depends(get_db)):
    updated_test = labtest_crud.update_lab_test_status(db, test_id, status)
    if not updated_test:
        raise HTTPException(status_code=404, detail="Lab Test Not Found")
    return {"message": "Lab Test status updated", "data": {
        "test_id": updated_test.test_id,
        "status": updated_test.status
    }}
