import RPi.GPIO as GPIO
import time

# Set pin numbering to board style
GPIO.setmode(GPIO.BOARD)

# Pin 11 is PWM output pin @ 50 Hz
GPIO.setup(11, GPIO.OUT)
servo = GPIO.PWM(11, 50)

# Set the initial duty cycle to 1.5 ms (90 degrees)
servo.start(7.5)

try:
    while True:
        # Turn to 0 degrees (CCW stop) and sleep 3 seconds
        servo.ChangeDutyCycle(2.5)
        time.sleep(3)
        
        # Turn to 180 degrees (CW stop) and sleep 3 seconds
        servo.ChangeDutyCycle(12.5)
        time.sleep(3)
        
        # Sweep from 180 to 0 degrees w/ 1 second in between steps
        i = 12.5;
        while (i > 2.5):
            i -= 1
            servo.ChangeDutyCycle(i)
            time.sleep(1)
        
        # Sweep from 0 to 180 degrees w/ 1 second in between steps
        while (i < 12.5):
            i += 1
            servo.ChangeDutyCycle(i)
            time.sleep(1)
        
except KeyboardInterrupt:
    servo.stop()
    GPIO.cleanup()
