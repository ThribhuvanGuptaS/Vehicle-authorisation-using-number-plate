from firebase import firebase
import os
import threading
from openalpr_ocr import ocr
import cv2
import time

switch = True
CN = [] # List of car numbers
NM = [] # List of user names for corresponding car numbers

fb = firebase.FirebaseApplication('https://link-to-firebase-real-time-database',None)

def updateDatabase(i=1,users='/Users/',firebase = firebase.FirebaseApplication('https://link-to-firebase-real-time-database',None)):
    print("Updating Local Database.............................")
    global CN
    global NM
    while(True):
        print("ID: "+str(i))
        path = users + str(i)
        res = firebase.get(str(path),'carNumber')
        if(str(res)=='None'):
            break
        nm = firebase.get(str(path),'name')
        CN.append(res)
        NM.append(nm)
        i = i+1
    print("Update Done!!")

def PlateCheck(var,cn=CN):
    global switch
    global fb
    check = False
    for i in range(0,len(cn)):
        if(cn[i]==var):
            print("Allowed to Enter!!")
            fb.patch('/RESULT/notify/',{'Entry update':'Registered'})
            switch = False
            check = True
            break
    if(check == False):
        print("Unauthorised Vehicle!")
        fb.patch('/RESULT/notify/',{'Entry update':'Unregistered'})
        print("Notification Sent!")

# Synchronise the local database buffer from the online database
updateDatabase()

PlatePrev = 'None'
PlateNew = 'None'
cap = cv2.VideoCapture('test2.mp4') 
# Check if camera opened successfully
if (cap.isOpened()== False): 
  print("Error opening video stream or file")

# Read until video is completed
while(cap.isOpened()):
  if(switch == True):

    # Capture frame-by-frame
    ret, frame = cap.read()
    if ret == True:

        gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        gray = cv2.rotate(gray,cv2.ROTATE_90_CLOCKWISE)
        
        cv2.imwrite('Frame.jpg',gray)
        PlateNew = ocr('Frame.jpg') # Detect the Plate number
        print(PlateNew)

        # Check for the number plate in the database
        if(len(PlateNew)==10):
            if(str(PlateNew) != str(PlatePrev)):
                if(str(PlateNew)!='None'):
                    PlateCheck(str(PlateNew))

        PlatePrev = PlateNew
        cv2.imshow('Frame',gray)

        if cv2.waitKey(25) & 0xFF == ord('q'):
            break 
    # Break the loop
    else: 
        break
 
# When everything done, release the video capture object
cap.release()
# Closes all the frames
cv2.destroyAllWindows()






