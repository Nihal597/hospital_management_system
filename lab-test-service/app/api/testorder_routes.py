from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from app.database import SessionLocal
from app.crud import testorder_crud

router = APIRouter()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.post("/api/testorders")
async def create_test_order(order: dict, db: Session = Depends(get_db)):
    try:
        new_order = await testorder_crud.create_test_order(db, order)
        return {"message": "Test Order Created", "data": {
            "order_id": new_order.order_id,
            "test_id": new_order.test_id,
            "doctor_id": new_order.doctor_id
        }}
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

@router.get("/api/testorders/doctor/{doctor_id}")
def get_doctor_test_order(doctor_id: int, db: Session = Depends(get_db)):
    tests = testorder_crud.get_test_order_by_doctor(db, doctor_id)
    if not tests:
        raise HTTPException(status_code=404, detail="Test Order Not Found For This Doctor")
    return tests

@router.get("/api/testorders/{order_id}")
def get_test_order(order_id: int, db: Session = Depends(get_db)):
    test = testorder_crud.get_test_order_by_id(db, order_id)
    if not test:
        raise HTTPException(status_code=404, detail="Test Order Not Found")
    return test

@router.delete("/api/testorders/{order_id}")
def delete_test_order(order_id: int, db: Session = Depends(get_db)):
    success = testorder_crud.delete_test_order_by_id(db, order_id)
    if not success:
        raise HTTPException(status_code=404, detail="Test Order Not Found")
    return {"message": f"Test Order {order_id} deleted successfully"}
