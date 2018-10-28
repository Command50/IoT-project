#include <OneWire.h>

//OneWire ds(2);

void setup() {
    Serial.begin(115200);
}

void loop() {
  int sensorPin;
int reading = analogRead(sensorPin);
  float voltage = reading * 5.0;
  voltage /=1024.0;
  Serial.print(voltage); Serial.println("volts");
  float temperatureC = voltage * 25;
   Serial.print(temperatureC); Serial.println("degress C");
   delay(1000);
  }
    
