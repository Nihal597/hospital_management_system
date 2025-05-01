from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base
import os 

DB_USER = os.getenv("DB_USER", "labtestuser")
DB_PASSWORD = os.getenv("DB_PASSWORD", "labtestpassword")
DB_HOST = os.getenv("DB_HOST", "labtest-db")
DB_NAME = os.getenv("DB_NAME", "lab_test_db")

DATABASE_URL = f"postgresql+psycopg2://{DB_USER}:{DB_PASSWORD}@{DB_HOST}/{DB_NAME}"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(bind=engine, autoflush=False, autocommit=False)
Base = declarative_base()
