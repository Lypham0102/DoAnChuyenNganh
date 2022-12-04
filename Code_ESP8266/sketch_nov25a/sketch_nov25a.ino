#include <ESP8266WiFi.h>                                                    // esp8266 library
#include <ArduinoJson.h>
#include <Adafruit_Sensor.h>
#include <FirebaseESP8266.h>                                                // firebase library
#include <DHT.h>  // dht11 temperature and humidity sensor library
#include "MQ135.h"

#define FIREBASE_HOST "esp8266-database-inventor-default-rtdb.firebaseio.com" // Khai bao link ket noi Firebase
#define FIREBASE_AUTH "Dj4DdXAwSzruVR494DL9K4UVFOzXbyg2hVHV8Yg1" // Khai bao mat khau ket noi Firebase

#define WIFI_SSID "Lydethuongggg" // Khai bao tén Wifi si dung
#define WIFI_PASSWORD "a123a123" // Khai béo mt khdu cia wifi
FirebaseData firebaseData; 
String path = "/";
FirebaseJson json;

#define PIN_MQ135 A0
const int DHTPIN = D2;
const int DHTTYPE = DHT11;
String fireStatus = "";
DHT dht(DHTPIN, DHTTYPE);   
MQ135 mq135_sensor = MQ135(PIN_MQ135);                                                  

void setup() {
  Serial.begin(9600);
  dht.begin();                
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);                                     //try to connect with wifi
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }                                          //print local IP address
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);      
  Firebase.reconnectWiFi(true);
  if(!Firebase.beginStream(firebaseData, path)){
      Serial.println("REASON: " + firebaseData.errorReason());
      Serial.println();
    }                  
    Serial.print("Connected with IP: ");
    Serial.println(WiFi.localIP());
    Serial.println();
}

void loop() { 
  
  float humidity = dht.readHumidity();                                              // Reading temperature or humidity takes about 250 milliseconds!
  float temperature = dht.readTemperature();  
  float ppm = mq135_sensor.getPPM();
    
  if (isnan(humidity) || isnan(temperature)) {                                                // Check if any reads failed and exit early (to try again).
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }

  Firebase.setFloat(firebaseData, "/TEMPERATURE", temperature);
  Firebase.setFloat(firebaseData, "/HUMIDITY", humidity);
  Firebase.setFloat(firebaseData, "/PPM", ppm);

   
}
