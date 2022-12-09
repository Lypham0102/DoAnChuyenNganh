#include <LiquidCrystal_I2C.h>
#include <Wire.h>
#include <ESP8266WiFi.h>                                                    // esp8266 library
#include <ArduinoJson.h>
#include <Adafruit_Sensor.h>
#include <FirebaseESP8266.h>                                                // firebase library
#include <DHT.h>  // dht11 temperature and humidity sensor library
#include "MQ135.h"

#define FIREBASE_HOST "esp8266-database-inventor-default-rtdb.firebaseio.com" // Khai bao link ket noi Firebase
#define FIREBASE_AUTH "Dj4DdXAwSzruVR494DL9K4UVFOzXbyg2hVHV8Yg1" // Khai bao mat khau ket noi Firebase

#define WIFI_SSID "Iphone11" // Khai bao tén Wifi si dung
#define WIFI_PASSWORD "12345678" // Khai béo mt khdu cia wifi
FirebaseData firebaseData; 
String path = "/";
FirebaseJson json;

int BUZZER = D5;

#define PIN_MQ135 A0
const int DHTPIN = D2;
const int DHTTYPE = DHT11;
String fireStatus = "";
DHT dht(DHTPIN, DHTTYPE);   
MQ135 mq135_sensor = MQ135(PIN_MQ135);    
LiquidCrystal_I2C lcd(0x27, 20, 4);
                                              

void setup() {
  Serial.begin(9600);
  dht.begin();     
  Wire.begin(D4,D3);     
  lcd.init();
  lcd.backlight();
  lcd.setCursor(0,0);
  lcd.print("Nguyen_Ly_Hieu_JA1");  

  pinMode(BUZZER, OUTPUT);
      
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

  if(Firebase.get(firebaseData, "/BUZZER")){
    if(firebaseData.dataType() == "string"){
      String buzzer = firebaseData.stringData();
      if(buzzer == "1"){
        Serial.println("Buzzer ON");
        digitalWrite(BUZZER, HIGH);
        }else{
          Serial.println("Buzzer OFF");
          digitalWrite(BUZZER, LOW);
          }
      }
    }
   if(ppm>40){
    digitalWrite(BUZZER, HIGH);
    Firebase.setFloat(firebaseData,"/BUZZER",1);
   }else {
      if(ppm < 40){
        digitalWrite(BUZZER, LOW);
        Firebase.setFloat(firebaseData, "/BUZZER", 0);
      }
    }

    if(temperature>32){
      digitalWrite(BUZZER, HIGH);
      Firebase.setFloat(firebaseData,"/BUZZER",1);
    }else {
      if(temperature < 16){
        digitalWrite(BUZZER, HIGH);
        Firebase.setFloat(firebaseData,"/BUZZER",1);
      }else {
        if(temperature > 16 && temperature <32){
          digitalWrite(BUZZER, LOW);
          Firebase.setFloat(firebaseData,"/BUZZER",0);
        }
    }
   }
      

  lcd.setCursor(0, 1);
  lcd.print("Nhiet do: ");
  lcd.setCursor(11, 1);
  lcd.print(temperature);
  lcd.setCursor(15, 1);
  lcd.print("(C)");
  lcd.setCursor(0, 2);
  lcd.print("Do am: ");
  lcd.setCursor(11, 2);
  lcd.print(humidity);
  lcd.setCursor(15, 2);
  lcd.print("(%)");
  lcd.setCursor(0, 3);
  lcd.print("Khi gas: ");
  lcd.setCursor(11, 3);
  lcd.print(ppm);
  lcd.setCursor(15, 3);
  lcd.print("(ppm)");
  delay(500);
   
}
