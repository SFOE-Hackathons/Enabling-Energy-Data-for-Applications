# FastAPI_Test_v02_02.py # not needed

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

# app = FastAPI()

import pandas as pd

note = ''

# -----------------------------------------------------------------
def filter_dates(df, start, end, note):
    print ("in filter date")
    
    
    
    if (start is not None):
        print ("start")
        # filtering here
        note += 'filt_start'
        newdf = df[(df['Datum'] >= start)] # & (df.carrier == "B6")]
        del df
        df = newdf
        #del df
        #df = newdf
        #return newdf, note
        
    if (end is not None):
        print ("end")
        # filtering here
        note += 'filt_end'
        newdf = df[(df['Datum'] <= end)] # & (df.carrier == "B6")]
        del df
        df = newdf
        #del data
        #data = newdf
        #df = newdf
        
    # if (start is None and end is None):
    #     newdf = df
    
    print ("out of filter")
    
    return df, note
    


#----------------------------------------------------------------------
def operations(url, start, end, note):
    df = pd.read_csv(url)
    print ("url = ", url)
    df, note = filter_dates(df, start, end, note)
    print ()
    df2 = df.T
    return df2, note


#########################################################################
# DEFINE API

# this works also without transpose: long
@app.get("/get-SPNT") # strom produktion nach Typp
def get_SPNT(start_date = None, end_date = None):  
    url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd104_stromproduktion_swissgrid.csv'
    df2, note2 = operations(url, start_date, end_date, note)
    return {'datasource': url, 'note' : note2},  {'data': df2}, {'status': 200}  # return data and 200 OK code


# this works only if transposed: wide
@app.get("/get-SV")
def get_SV(start_date = None, end_date = None):    
      url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd103_stromverbrauch_swissgrid_lv_und_endv.csv'
      df2, note2 = operations(url, start_date, end_date, note)
      return {'datasource': url, 'note' : note2}, {'data': df2} , {'status': 200} # return data and 200 OK code


# this works only if transposed: wide
@app.get("/get-SVGS")
def get_SVGS(start_date = None, end_date = None):        
      url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd103_stromverbrauch_geschaetzt_swissgrid.csv'
      df2, note2 = operations(url, start_date, end_date, note)
      return {'datasource': url, 'note' : note2}, {'data': df2}, {'status': 200}  # return data and 200 OK code
  
# this works also without transpose: long
@app.get("/get-gas")
def get_gas(start_date = None, end_date = None):        
      url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd102_fuellstand_gasspeicher.csv'
      df2, note2 = operations(url, start_date, end_date, note)
      return {'datasource': url,  'note' : note2}, {'data': df2}, {'status': 200}  # return data and 200 OK code

# this works also without transpose: long
@app.get("/get-gasinout")
def get_gasinout(start_date = None, end_date = None):        
      url = 'https://bfe-energy-dashboard-ogd.s3.amazonaws.com/ogd101_gas_import_export.csv'
      df2, note2 = operations(url, start_date, end_date, note)
      #return {'datasource': url, 'status': 200, 'note' : note2, 'data': df2}  # return data and 200 OK code
      return {'datasource': url, 'note' : note2}, {'data': df2}, {'status' : 200}  # return data and 200 OK code

    
