from sqlalchemy import Column, Integer, TIMESTAMP, String, ForeignKey, func
from app.database import Base

class TestOrder(Base):
    __tablename__ = "test_orders"

    order_id = Column(Integer, primary_key=True, index=True)
    test_id = Column(Integer, ForeignKey("lab_tests.test_id", ondelete="CASCADE"), nullable=False)
    doctor_id = Column(Integer, nullable=False)
    order_date = Column(TIMESTAMP, server_default=func.now())
    status = Column(String(50), default="Ordered")
