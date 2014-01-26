# Simple Java Server Faces (JSF) validation web application

### Problem statement

 - Create a simple web application to display and validate a form with the following structure. 
 - On successful completion of all fields, show a simple submission page. 
   No actual processing of the form required. 
 - Use JSF 2.0 components.
 - Implement a custom validator for the street address 1 validation and the age check.
 - Validate each field using the JSF validators as the field loses focus. 
 - Treat the 3 inputs for phone as one field.

### Form Structure

    (a) Name:
    (a)   First           Letters only
    (a)   Last            Letters only
    (a) Date of birth
          [See DOB rules]
    (a) Residential Address:
    (a)   Street Address 1    Numbers and letters. No PO Box.
    (a)   Street Address 2    Numbers and letters. No PO Box.
    (b) Mail to Residential address:
          Yes or No   [See MailTo rules]
    (b) Mailing Address
    (b)   Street Address 1  [ Numbers and letters only]
    (b)   Street Address 2  [ Numbers and letters only]
    (c) Primary Phone
    (c)   Type Area Code Phone Number [See phone rules]
    (a) Submit Button

### Validation Rules:

#### DOB rules: 
    Date format is dd/mm/yyyy
    Age at form submission must be 16 or greater.

#### MailTo rules:
    The mailing address fields are initially hidden. 
    If the user selects No for the Mail to Residential Address field, then show the mailing address fields.

#### Phone Rules:
    - Type is one of Home, Work or Mobile
    - Area code is one of 02, 03, 07 or 08. Area code is not displayed for mobile phone numbers.
    - Phone accepts 8 digits for home or work number or 10 digits for mobile.
    - Type defaults to Home for primary phone and mobile for alternate.

## Installation

#### Build project and import into Eclipse

```
$mvn clean package
```

Then import to your favourite IDE (mined is Eclipse)

```
$mvn eclipse:eclipse 
```

then from Eclipse, File->Import 

#### Build and deploy using Maven to Apache Tomcat

To actually run it just use the supplied script 

```
build-and-deploy.sh
```

    #!/bin/bash
    mvn clean package
    # NOTE: edit this to suite your deployment environment
    export DEPLOYMENT_DIR=~/Apps/apache-tomcat-7/webapps
    cp target/jsf-validation-example.war $DEPLOYMENT_DIR  

## Author

[Burin Choomnuan](https://github.com/agilecoders)