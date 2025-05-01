from fastapi import FastAPI
from app.database import Base, engine
from app.api import labtest_routes, testorder_routes

app = FastAPI()

# Create all tables
Base.metadata.create_all(bind=engine)

app.include_router(labtest_routes.router)
app.include_router(testorder_routes.router)

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("app.main:app", host="127.0.0.1", port=8000, reload=True)