# Archetype - frontend-firebase-auth
TurnOnline.biz Ecosystem Frontend App skeleton with Firebase Authentication built from scratch for Google App Engine Java standard environment

# Setup
## Create Google App Engine project
Prior to create archetype you need to create new project (or use already created). You can create new Google App engine project here:

```bash
https://console.cloud.google.com/projectcreate
```

> Store you project id (not project name!) - you will need it to setup Firebase and also for running of archetype.

## Create Firebase project
If you have successfully created Google App engine project use project id to configure firebase. Go to `https://console.firebase.google.com`
and click [Add project] button. New popup will show where you select your project (Firebase will show all projects for logged in user).
Next click on [Create project] button and you are done.

## Run archetype
In order to generate microservice template from the archetype use the following command. You will be prompted to specify the information 
about the new project you want to create; the standard maven GAV (Group, Artifact, Version) and next ones needed for Google Cloud: **_AppEngineId_**.

```bash
    mvn archetype:generate \
     -DarchetypeGroupId=biz.turnonline.ecosystem.archetype \
     -DarchetypeArtifactId=turnonline-frontend-firebase-auth-archetype \
     -DarchetypeVersion=1.0
```

## Configuration of generated project
In order to run properly your project you have to make additional setup. Open your project and search for 
```bash
{project}/src/main/resources/identity.properties
```
Read comments on all properties and fill them accordingly. 

## Running your project
After successful setup of archetype you can run your project (for instance use Google Cloud Tools plugin). Go to 
`http://localhost:8080` where you will see more information about next steps and useful links.
