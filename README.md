# Home Items Inventory 
It is a simple application to store your items. Using this application you can see where you have an items. You can add or remove items. You can check where you have items by selecting location or inserting an item name.


# About:
I created this app to see where i have items in my home. It's really usefull when you need some item and you don't know where it is or when you don't know if you have ever had this item. I used Hibernate framework to connect and manage database. I made an authentication with hashed password (MD5). It secures application before other people. Settings file is serialized that's why can quick and easy configure this app. All application is scallable. GUI look the same in every resolutions.

# How to download:
click 'out/artifacts/Home_Items_Inventory_jar' in master branch and download 'Home_Items_Inventory.jar'. 

# How to use: 
1. create database.
2) create table:
3) 'items' with columns: id,Ean,Name,Location,Qty,Items_comment,Info_date,AppId,UserId
4) 'users' with columns: id,login,password,name
4) download and configure application in 'SETTINGS' panel.
5) create bash file: java -jar path_to_jar_file/Home_Items_Inventory.jar

# Example:
Login:
![image](https://github.com/user-attachments/assets/2cb56e9b-3c73-4970-ba36-2d17e3bfdcb5)

Read:
![image](https://github.com/user-attachments/assets/4ed84a88-6fdf-4e33-a34a-4e2def2cdd63)

Remove:
![image](https://github.com/user-attachments/assets/8e9d3d29-395f-4e89-8bad-3e2f92d70c92)

Add:
![image](https://github.com/user-attachments/assets/ec8fb0fe-7c8c-41cd-a02e-18708175544f)

Informations:
![image](https://github.com/user-attachments/assets/cc23086c-c06e-41b9-bd17-afb649b8642e)

Settings:
![image](https://github.com/user-attachments/assets/06355ebd-0a75-4e50-9002-183921131842)


# Requirements:
- Windows 10.
- Java 17 or higher.
