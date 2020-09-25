# SPS-MainProject
This repository contains the code for the project done during Google Software Product Sprint by TEAM 87.

The aim of the project is to build a web portal to provide a platform for any organization’s employees to post any new product idea and get their peer’s reviews and detailed analysis using statistical and sentiment analysis techniques. 

Read Design Document or watch the demo video for details about the project.

Demo Video: https://youtu.be/jyVdJ3zCKEo

To run the code on your local machine, follow the following steps:

- Set your default Java version to Java 8. To do that, first open your .bashrc file by running this command: edit ~/.bashrc

- Copy this line to the end of the file: sudo update-java-alternatives -s java-1.8.0-openjdk-amd64 && export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre

- Save and close the .bashrc file, and then execute it using this command: source ~/.bashrc

- Then move to the directory IdeaPortal: cd IdeaPortal

- Execute the following command to run the server: mvn package appengine:run

The first time you run this command, Maven will automatically download all of the libraries and files required to run a server, so it might take a few minutes. When the command completes, you'll see this in the console:

INFO: Dev App Server is now running

Then you can view the website running on the localhost port 8080
