# daycare-project-dev

-----Test--------
URL (GET): APP_URL/api/test
  * Sample Response
  {
    "hello": "Welcome!"
  }

------Registration API-------
    URL (POST): APP_URL/api/register 

    * Sample Request
    {
      "name": "Hello name",
      "email": "dummy@dummy.com",
      "password": "q842dkfjj87dd"
    }

* Sample Response:
    {
        "message": "Successfully registered!",
        "status": "OK"
    }

* Failed Response:
    {
        "error": "User is already registered!",
        "status": "EMAIL_EXISTS"
    }


----Login API-------
URL (POST): APP_URL/api/login 

    * Sample Request:
    {
        "email": "dummy@d.com",
        "password": "#kjsd#45kj87dd"
    }

* Sample Response (Header)
    {
       "authentication":"dymmy.eyJzdWIiOiJkdW1teUBkLmNvbSIsInJvbGVzIjpbXSwiZXhwIjoxNjc0NjYwOTc3fQ.z3KSyGkgTiX3zSmxcfPXxmzH0vJibtlEp_LJ4I_ZIJ7E8tRksO7ROqEF23BUnUaCs089QBxQmXJU7sBKQ"
    }

* Sample Failed Response
    {
        "authentication" : ""
    }

This token you have to add your request token header.

----Pin Verification-------
URL (GET): APP_URL/api/verifypin/{pin}
  sample response:
  Success:
  {
    "status": "OK"
  }
  Failed:
  {
    "status": "NOT_OK"
  }
  
-----Baby-------
* Add a Baby

Sample request:
{
    "user": {
                "id": 21,
                "name": "Motiur Rahman Munna",
                "email": "munna.cse.ju@gmail.com",
                "verificationPin": "72893",
                "isVerified": true,
                "saveFirstPassword": "$2a$10$pVEc9BY6NAHnKk0HLukfOuOai5.DuPRTWShDyG3ajpLAAm9ZKzZG.",
                "userRole": "ADMIN"
            },
            "babyName": "Hello baby",
            "babyAge": 2,
            "motherName": "Firoza Begum",
            "fatherName": "Hello",
            "contactNumber": "123546",
            "address": "Hpme",
            "fevoriteFood": "Dood",
            "isSicked": false
}
            
Sample Response:
{
    "baby": {
        "id": 46,
        "user": {
            "id": 21,
            "name": "Motiur Rahman Munna",
            "email": "munna.cse.ju@gmail.com",
            "verificationPin": "72893",
            "isVerified": true,
            "saveFirstPassword": "$2a$10$pVEc9BY6NAHnKk0HLukfOuOai5.DuPRTWShDyG3ajpLAAm9ZKzZG.",
            "userRole": "ADMIN"
        },
        "babyName": "Hello baby",
        "babyAge": 2,
        "motherName": "Firoza Begum",
        "fatherName": "Hello",
        "contactNumber": "123546",
        "address": "Hpme",
        "fevoriteFood": "Dood",
        "isSicked": false
    },
    "status": "OK"
}

* Find all baby 
  URL (GET): APP_URL/api/findAllBaby
  
  sample response:
  {
    "babies": [
        {
            "id": 44,
            "user": {
                "id": 21,
                "name": "Motiur Rahman Munna",
                "email": "munna.cse.ju@gmail.com",
                "verificationPin": "72893",
                "isVerified": true,
                "userRole": "ADMIN"
            },
            "babyName": "Dffg",
            "babyAge": 4,
            "motherName": "0198754%:'!%&*",
            "fatherName": "Hello",
            "contactNumber": "75685253",
            "address": "Hpme",
            "fevoriteFood": "Dood",
            "isSicked": false
        }
    ],
    "status": "OK"
}


