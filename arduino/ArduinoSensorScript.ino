#include <Arduino.h>

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>

#include <ESP8266HTTPClient.h>

#define USE_SERIAL Serial

ESP8266WiFiMulti WiFiMulti;

const char* ssid = "Orange-FDD2";
const char* password = "uEmDKqt5";
String server = "192.168.1.110:8080";
String sensorName = "pasillo";

boolean registered = false;

void setup() {

    USE_SERIAL.begin(115200);
   // USE_SERIAL.setDebugOutput(true);

    USE_SERIAL.println();
    USE_SERIAL.println();
    USE_SERIAL.println();

    for(uint8_t t = 4; t > 0; t--) {
        USE_SERIAL.printf("[SETUP] WAIT %d...\n", t);
        USE_SERIAL.flush();
        delay(1000);
    }

    WiFiMulti.addAP(ssid, password);

}

void loop() {
    // wait for WiFi connection
    if((WiFiMulti.run() == WL_CONNECTED)) {
      if(!registered) 
      {
        //Register sensor.
        registerSensor();
        registered = true;
      }
      else {
        sendSensorUpdate();
        delay(3000);
      }
      
    }
    else {
      delay(1000);
    }
}

void registerSensor() 
{
    post("http://" + server + "/sensors", "{\"name\": \"" + sensorName + "\"}"); 
}

void sendSensorUpdate() {
  int temperature = random(1, 32);
  int humidity = random(40, 70);
  String body = "{\"sensorName\": \"" + sensorName + "\", \"temperature\": " + temperature + ", \"humidity\": " + humidity + "}";
  String url = "http://" + server + "/weatherRecords";

  post(url, body);
}

void post(String url, String body) {
    HTTPClient http;
    USE_SERIAL.print("[HTTP] begin...\n");
    http.begin(url); //HTTP
    http.addHeader("Content-Type", "application/json");
  
    USE_SERIAL.print("[HTTP] POST...\n");
    // start connection and send HTTP header
    int httpCode = http.POST(body);
  
    // httpCode will be negative on error
    if(httpCode > 0) {
        // HTTP header has been send and Server response header has been handled
        USE_SERIAL.printf("[HTTP] POST... code: %d\n", httpCode);
  
        // file found at server
        if(httpCode == HTTP_CODE_OK) {
            String payload = http.getString();
            USE_SERIAL.println(payload);
        }
    } else {
        USE_SERIAL.printf("[HTTP] POST... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }
  
    http.end();  
  
}

