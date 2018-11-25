import pyodbc

cnxn = pyodbc.connect('DRIVER={FreeTDS};SERVER=ServerName;PORT=1433;DATABASE=DatabaseName;UID=userName;PWD=Password;TDS_Version=8.0;')
cursor = cnxn.cursor()
cursor.execute("SELECT SensorName, Temperature From [dbo].[Sensor]")
row = cursor.fetchone()
while row:
    print (str(row[0]+" "+row[1]))
    row = cursor.fetchone()
