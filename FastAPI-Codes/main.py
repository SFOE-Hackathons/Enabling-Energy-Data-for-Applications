# -*- coding: utf-8 -*-
"""
Created on Thu Mar 23 15:31:56 2023

@author: U80827488
"""

from fastapi import FastAPI
import pandas as pd
import requests, urllib3
import io

 
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)
 
app = FastAPI()

@app.get("/test")
# async def index():
#     return {"message": "Hallo Welt!"}
def get():   
    proxies = {
          "http":'http://proxy-bvcol.admin.ch:8080',
          "https":'http://proxy-bvcol.admin.ch:8080'
        }
    url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd102_fuellstand_gasspeicher.csv'
    s = requests.get(url, proxies = proxies, verify = False).text
    df = pd.read_csv(io.StringIO(s))
    df1=df.T
    return {'data': df1}, 200  # return data and 200 OK code