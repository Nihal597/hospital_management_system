from sqlalchemy import Column, Integer, String, Date, Text, TIMESTAMP, func
from app.database import Base

class LabTest(Base):
    __tablename__ = "lab_tests"

    test_id = Column(Integer, primary_key=True, index=True)
    test_name = Column(String(255), nullable=False)
    patient_id = Column(Integer, nullable=False)
    test_date = Column(Date, nullable=False)
    result = Column(Text)
    status = Column(String(50), default="Pending")
    created_at = Column(TIMESTAMP, server_default=func.now())
    updated_at = Column(TIMESTAMP, server_default=func.now(), onupdate=func.now())
