# SSRS-Element-Centering-Tool
Simple program to centre stuff on an SSRS report, based on element and page sizes. 


# *What?*

Have you ever wanted to allign something in SSRS report builder, but have the dreaded problem that the alignement options are greyed out?

![align](https://user-images.githubusercontent.com/35103224/37715286-89e98c08-2d13-11e8-93e8-5c518898a72d.JPG)

This is for you!



# *Why?*

For whatever reason, centering an element (text box/ picture/matrix ect) in report builder just doesn't work sometimes. 
To centrally align elements I have been using the 'Location' variable under the 'Positon' menu -
![pos](https://user-images.githubusercontent.com/35103224/37714969-bca7804c-2d12-11e8-9fae-4a0746a6349f.JPG)

Using the ground breaking mathematical formula of **Centre = (Page size / 2 ) - (Element size / 2 )**, any element will now be perfectly centered when you enter the result into the elements X-axis location variable. 


# *How?*

Using absolutely groundbreaking JavaFx and the above mentioned formula, simply enter the width of the report page and the width of the element to be centered, choose the correct measurement unit and **calculate**. Here's a worked example;

1) Get the size of the report page and element 

![pagesize](https://user-images.githubusercontent.com/35103224/37716291-1106430a-2d16-11e8-92a3-d6dbcf6436a2.png)

![elementsize](https://user-images.githubusercontent.com/35103224/37716295-14482c86-2d16-11e8-8d32-46f6bde20df0.png)

2) Enter in the centr app and select correct units (both units don't have to be the same)

![appshot](https://user-images.githubusercontent.com/35103224/37716417-655d3850-2d16-11e8-8ccf-af2d2aa58104.JPG)

3) Press calculate and fail to contain your excitement 

![result](https://user-images.githubusercontent.com/35103224/37716521-a32b3b3c-2d16-11e8-902e-461e347d71b5.JPG)

4) Enter the result and marvle at your now perfectly centered element! 

![final](https://user-images.githubusercontent.com/35103224/37716620-d6d13540-2d16-11e8-85c1-8fed669d4e66.JPG)


*Note: This is all based on the premise that Report builder measures the 'distance' variable from the far left side of the element. Should this change at any point, burn this repository*




*//JB 21/03/18*
