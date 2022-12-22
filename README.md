# test-app

## how to start using calculator within test-app?

### prerequisits:
 - `Java 17` (as developemnt kit sdkman artifact was used: Java.net 17-open)           
 - `Maven 3.6.3`

### steps
 - clone application
 - build with `mvn clean install`
 - running application: `mvn spring-boot:run`


example formula:
"3/0/10000-9/0" can be obtained by sending HTTP GET request (simply type into adress bar of browser) :

http://localhost:8080/evaluate/3/0/10000-9/0


you shall get `NaN` as a result for above example `(Infinity/Infinity=NaN)`. More ordinary expressions bring more ordinary result 🚀.

You can also use Postman collection along with postman environment loacted in `postman` foder in root directory of this repo.

Enjoy!
