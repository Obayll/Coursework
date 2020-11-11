import RPi.GPIO as GPIO
import time

# GPIO numbering
GPIO.setmode(GPIO.BCM)

# GPIO17 & GPIO18 will be outputs
GPIO.setup(17, GPIO.OUT)
GPIO.setup(18, GPIO.OUT)

# Initiate "railroad crossing lights"
try:
    while (True):
        GPIO.output(17, True)
        GPIO.output(18, False)
        time.sleep(0.5)
        GPIO.output(17, False)
        GPIO.output(18, True)
        time.sleep(0.5)

# Catch keyboard interrupt
except KeyboardInterrupt:
    print("Caught interrupt.")

# Clean GPIO pins on exit
finally:
    print("Cleaning pins and exiting program.")
    GPIO.cleanup()