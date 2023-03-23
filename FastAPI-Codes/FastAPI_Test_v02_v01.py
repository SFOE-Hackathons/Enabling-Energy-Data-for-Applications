# FastAPI_Test_v02_01.py

# following: https://anderfernandez.com/en/blog/how-to-create-api-python/

# =============================================================

from fastapi import FastAPI
app = FastAPI()

# @app.get("/")
# def hello():
#   return {"message": "Hello world!"}

# @app.get("/")
# def hello_2(name: str):
#   return {"message": "Hello " + name + " and the world!"}


# from fastapi import FastAPI

app = FastAPI()

@app.get("/get-SPNT") # strim produktion nach Typp
def get_SPNT():

    import pandas as pd
    # url ='https://gist.githubusercontent.com/curran/a08a1080b88344b0c8a7/raw/0e7a9b0a5d22642a06d3d5b9bcbad9890c8ee534/iris.csv'
    url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd104_stromproduktion_swissgrid.csv'
    data = pd.read_csv(url)

    return data