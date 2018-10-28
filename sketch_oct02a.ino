#include <OneWire.h>

//OneWire ds(2);

int Relay = 2;

void setup() {
    Serial.begin(115200);
    pinMode(Relay, OUTPUT);
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
  if (temperatureC > 50) {
    digitalWrite(Relay, LOW);  
    }
    else {
      digitalWrite(Relay, LOW); // реле увімкнено
      delay(2000);
      digitalWrite(Relay, HIGH); // реле вимкнено
      delay(2000);  
    }
  }
