import pyodbc
import os
import glob
import time
import pyodbc 
os.system('modprobe w1-gpio')
os.system('modprobe w1-therm')
 
base_dir = '/sys/bus/w1/devices/'
device_folder = glob.glob(base_dir + '28*')[0]
device_file = device_folder + '/w1_slave'
 
def read_temp_raw():
    f = open(device_file, 'r')
    lines = f.readlines()
    f.close()
    return lines
 
def read_temp():
    lines = read_temp_raw()
    while lines[0].strip()[-3:] != 'YES':
        time.sleep(0.2)
        lines = read_temp_raw()
    equals_pos = lines[1].find('t=')
    if equals_pos != -1:
        temp_string = lines[1][equals_pos+2:]
        temp_c = float(temp_string) / 1000.0        
        return temp_c

def executestatement(tempareture):
    temparetureString = str(tempareture)
    cnxn = pyodbc.connect('DRIVER={FreeTDS};SERVER=mserver2018.database.windows.net;PORT=1433;DATABASE=Data_from_sensors;UID=maks177;PWD=vualehvist05051998M;TDS_Version=8.0;')
    cursor = cnxn.cursor()
    cursor.execute("Update Sensor SET Temperature="+temparetureString+" Where SensorID = '1'")
    cnxn.commit()

def statistic(temperature1):
    temperatureString1 = str(temperature1)
    cnxn1 = pyodbc.connect('DRIVER={FreeTDS};SERVER=mserver2018.database.windows.net;PORT=1433;DATABASE=Data_from_sensors;UID=maks177;PWD=vualehvist05051998M;TDS_Version=8.0;')
    cursor1 = cnxn1.cursor()
    cursor1.execute("insert into Statistic(CustomerID, SensorID, Temperature, SensorName) values (?, ?, ?, ?)", '1', '2', temperatureString1, 'TestSensor')
    cnxn1.commit()
               	
def main():
    while True:
        temp = read_temp()
        print(temp)
        temp1 = read_temp()
        executestatement(temp)
        statistic(temp1)
        time.sleep(1)
if __name__=='__main__':
    main()

