from firebase import firebase
import os

# Function to check a registered vehicle number from the database
def plateCheck(i=1,userss='/Users/',firebase = firebase.FirebaseApplication('https://friendchat-2aedf.firebaseio.com',None)):
    
    var = raw_input("Plate to be found: ")
    var = str(var)

    # Check for the user in the database
    while(True):
        print("ID: "+str(i))
        
        path = userss + str(i)
        print("Path: "+str(path))
        
        res = firebase.get(str(path),'carNumber')

        # If car not found update the same in database
        if(str(res) == 'None'):
            print('Car not found')
            firebase.post('/RESULT/',{'Entry update':var})
            break
        
        print("carNumber: "+str(res))
        
        nm = firebase.get(str(path),'name')
        
        # If car found, Print the user name
        if(str(res) == var):
            print('Car found of user: '+str(nm))
            break
        
        print("name: "+str(nm))
        
        i = i+1
    
