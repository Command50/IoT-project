import pyodbc

cnxn = pyodbc.connect('DRIVER={FreeTDS};SERVER=ServerName;PORT=1433;DATABASE=DatabaseName;UID=userName;PWD=Password;TDS_Version=8.0;')
cursor = cnxn.cursor()
cursor.execute("INSERT INTO SENSOR (Temperature, SensorName, CustomerID, SensorID) VALUES ('25', 'DS18b203', '2', '53')")
cnxn.commit()
cursor.execute("Update Sensor  SET Temperature='41' Where SensorID = '52'")
cnxn.commit()

