#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <DHT.h>  // Include the DHT sensor library

// LCD Setup
LiquidCrystal_I2C lcd(0x27, 16, 2);  // Initialize LCD with I2C address 0x27

// DHT11 Sensor Setup
#define DHTPIN 2       // DHT11 Data Pin connected to Arduino Pin 2
#define DHTTYPE DHT11  // DHT11 sensor type
DHT dht(DHTPIN, DHTTYPE);  // Initialize DHT sensor

// Relay and Moisture Sensor Setup
int relayPin = 8;                    // Relay module for water pump
int moistureSensorPin = A0;           // Soil Sensor input at Analog PIN A0
int outputValue = 0;                  // Stores soil moisture level
int dryThreshold = 30;                // Moisture level below which pump turns on (percentage)
int wetThreshold = 70;                // Moisture level above which pump turns off (percentage)
bool isPumping = false;               // Track the pump status (ON or OFF)

// Raindrop Sensor Setup
int rainSensorPin = A3;               // Raindrop sensor pin
int rainThreshold = 300;              // Value below which rain is detected

void setup() {
  Serial.begin(9600);

  pinMode(relayPin, OUTPUT);
  digitalWrite(relayPin, HIGH);   // Ensure the relay is off initially (active LOW)
  
  // Initialize the LCD
  lcd.begin(16, 2);  // Initialize LCD for 16x2 characters
  lcd.backlight();    // Turn on LCD backlight
  lcd.setCursor(0, 0);
  lcd.print("Initializing...");

  // Initialize the DHT11 sensor
  dht.begin();

  Serial.println("Initializing system...");
  delay(2000);  // Initial delay for system readiness
  lcd.clear();  // Clear LCD after initialization message
}

// Function to read and map soil moisture sensor values
int readMoistureLevel() {
  int sensorValue = analogRead(moistureSensorPin);
  
  // Map the sensor value from 550 (dry) to 10 (wet) into a percentage (0 to 100)
  int moistureLevel = map(sensorValue, 550, 10, 0, 100);

  // Ensure the moisture level is clamped within the range 0 to 100%
  moistureLevel = constrain(moistureLevel, 0, 100);
  
  return moistureLevel;
}

// Function to control the water pump based on moisture level
void controlPump(int moistureLevel) {
  if (moistureLevel < dryThreshold) {
    if (!isPumping) {  // Only turn on if not already pumping
      digitalWrite(relayPin, LOW);  // Turn ON the pump (active LOW relay)
      isPumping = true;
      Serial.println("Watering the plant (Pump ON)...");
      lcd.setCursor(0, 1);
      lcd.print("Pump ON");
    }
  } 
  else if (moistureLevel > wetThreshold) {
    if (isPumping) {  // Only turn off if it's currently on
      digitalWrite(relayPin, HIGH);  // Turn OFF the pump
      isPumping = false;
      Serial.println("Pump OFF (Soil moist)");
      lcd.setCursor(0, 1);
      lcd.print("Pump OFF");
    }
  }
}

// Function to update the LCD
void updateLCD(int moistureLevel, float temperature, float humidity, bool isRaining) {
  // Display moisture level on the first line
  lcd.setCursor(0, 0);
  lcd.print("Moist:");
  lcd.print(moistureLevel);
  lcd.print("%");  // Add spaces to clear previous content

  if (isRaining) {
    lcd.print("|Rain ");
  } else {
    lcd.print("NoRain");
  }
  // Display temperature and rain status on the second line
  lcd.setCursor(0, 1);
  lcd.print("T: ");
  lcd.print(int(temperature));
  lcd.print("C ");
  
  
}

void loop() {
  // Read current moisture level
  outputValue = readMoistureLevel();

  // Read DHT11 sensor values
  float temperature = dht.readTemperature();  // Read temperature in Celsius
  float humidity = dht.readHumidity();        // Read humidity in percentage

  // Check if the readings are valid
  if (isnan(temperature) || isnan(humidity)) {
    Serial.println("Failed to read from DHT sensor!");
    lcd.setCursor(0, 1);
    lcd.print("Sensor error!    ");
    return;
  }

  // Check rain status
  int rainValue = analogRead(rainSensorPin);
  bool isRaining = (rainValue < rainThreshold);

  // Print the moisture level to the Serial Monitor
  Serial.print("Moisture level: ");
  Serial.print(outputValue);
  Serial.println("%");

  // Print temperature, humidity, and rain status to Serial Monitor
  Serial.print("Temperature: ");
  Serial.print(temperature);
  Serial.print(" C, Humidity: ");
  Serial.print(humidity);
  Serial.print("%, Rain detected: ");
  Serial.println(isRaining ? "Yes" : "No");

  // Update the LCD with current values
  updateLCD(outputValue, temperature, humidity, isRaining);

  // Control the water pump based on current moisture level
  controlPump(outputValue);

  delay(5000);  // Check every 5 seconds to avoid excessive sensor polling
}
