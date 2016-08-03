
<strong> Door Open Detection: (Sensor Change)</strong> <br />
On app start the sensors are registered and the acclerometer sensor values and magnetometer sensor values is passed through a low pass filter to get a filter the unequalities in the sensor and then the values are used to get the Yaw, Pitch and Roll to detect the angle change. In Our case the pitch value is constant since the device is mounted potrait. The door status is detected by the shake produced during door opening and the angle change form yaw and roll. So two states are outcomes of this Module Door-Open/Door-Closed. <br/>
<br />
<strong> Intent Manager: </strong><br />
Once a call for door open is recevied the Intent manager creates a synchronised lock for the call and thereby calling the Face detecting activity. On more sensor change to Door-Closed the intent manager releases the lock and returns the screen to the Sensor Change Detection State.<br/>
<br />
<strong> Face Detection: </strong><br />
Here the permission for using the camera is obtained from the manifest file and graphics definitons needs to added to the face is obtained and then the face detection starts from the camera source and then the graphics is drawn on a canvas using the face width and height and pixel position by the way starting the image storage and upload module.<br />
<br />
<strong> Image Capture and Upload:</strong> <br />
Once the Face Detection Activity is Started the Image is captured and saved, Since the overlay is drawn over the camera source the overlay wil not appear in the saved image. The app uses the Http Connection Client api built in to upload the images to the server. <br />
<br />
<strong> Backend - Web App :</strong><br />
Once a POST call is made the web app recieves the image, It extracts the timestamp and the happiness percentage from it and saves it along with the image in the database. when an entry is made the database angular makes uses of its MVC features to show the updated entries in the User Inter face <br />
<br />
