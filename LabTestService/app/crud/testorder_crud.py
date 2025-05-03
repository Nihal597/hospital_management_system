import httpx
from sqlalchemy.orm import Session
from app.models.test_order import TestOrder

# USER_SERVICE_URL = "http://localhost:9000"
USER_SERVICE_URL = "http://user-service:9000"
USERNAME = "anshul"
PASSWORD = "anshul@123"

async def get_jwt_token() -> str:
    async with httpx.AsyncClient() as client:
        response = await client.post(
            f"{USER_SERVICE_URL}/api/users/generateToken",
            json={"username": USERNAME, "password": PASSWORD}
        )
        response.raise_for_status()
        return response.text  # because the Spring Boot service returns token as plain text
    
# Call doctor API with Bearer token
async def validate_doctor(doctor_id: int) -> bool:
    token = await get_jwt_token()
    headers = {"Authorization": f"Bearer {token}"}
    async with httpx.AsyncClient() as client:
        response = await client.get(f"{USER_SERVICE_URL}/api/users/doctors/{doctor_id}", headers=headers)
        return response.status_code == 200

async def create_test_order(db: Session, order_data: dict):
    # doctor_exists = await validate_doctor(order_data['doctor_id'])
    # if not doctor_exists:
    #     raise Exception("Doctor not found in User Service.")

    new_order = TestOrder(**order_data)
    db.add(new_order)
    db.commit()
    db.refresh(new_order)
    return new_order


def get_test_order_by_id(db: Session, order_id: int):
    return db.query(TestOrder).filter(TestOrder.order_id == order_id).first()

def get_test_order_by_doctor(db: Session, doctor_id: int):
    return db.query(TestOrder).filter(TestOrder.doctor_id == doctor_id).all()

def delete_test_order_by_id(db: Session, order_id: int):
    order = db.query(TestOrder).filter(TestOrder.order_id == order_id).first()
    if order:
        db.delete(order)
        db.commit()
        return True
    return False

def update_test_order_status(db: Session, order_id: int, new_status: str):
    order = db.query(TestOrder).filter(TestOrder.order_id == order_id).first()
    if not order:
        return None
    order.status = new_status
    db.commit()
    db.refresh(order)
    return order
