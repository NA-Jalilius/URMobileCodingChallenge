# URMobileCodingChallenge

This is a challenge, part of internship application process at United Remote.

# Technologies used

The mobile platform : Android, using Java.

# About the project

More info about the idea of the project refer to :
https://github.com/hiddenfounders/mobile-coding-challenge

As a REST client for my application I used Retrofit, which is an open source java library which can be used as web service client API. It helps construct web service access url,  send the request to web service by numerous HTTP method such as GET, POST, DELETE etc.It can send parameters such as url query string or html form fields value to web server also.

When the web service return data to client, it can use data converter to parse out the response data by the data format ( json, xml etc) and return related java object list (I've used Gson which parses JSON to and from Java objects by default. All I have to add is defining the class (a POJO) of the wanted response object and the response had been mapped to an object of this class).

For more information about the Retrofit library, please refer to  : https://github.com/square/retrofit

For my REST client, I've used Restrofit synchronus request to get the data. Althought we can use asynchronus requests also !

A picture of the app execution :

And the chosen symbolic Icon was (You can guess what the abrv. stands for :D )

![alt text] (../master/app/src/main/ic_launcher_icone-web.png "My Icon")

