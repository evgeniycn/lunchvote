# lunchvote

Voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

SoapUI project for tests: lunchvote-soapui-project.xml

API details:
-----------------------------
**User part:**
-----------------------------

**Vote for today dish**

GET URL: /rest/users/vote/{id}

SUCCESS RESPONSE:
>{"id":100026, "userId":100000, "date":"2017-07-15", "restrauntId":100011}

FAILED RESPONSE:
>{"url": "/rest/users/vote/100012", "cause": "TimeLimitExceededException", "details": ["Vote time is expired for today"]}
>{"url": "/rest/users/vote/1", "cause": "NotFoundException", "details": ["No menu from this restraunt available for today"]}


**Admin part:**
-----------------------------
**Get user by id:**

GET URL: /rest/admin/users/{id}

SUCCESS RESPONSE:
>{"id": 100000, "name": "User1", "email": "user1@user.com", "password": "$2a$10$/DexUoa4UClouvESi6j7OuYf9n8XctfKJivJR0C9wmI7F4KXMOgQK", 
"roles": ["ROLE_USER"]}

FAILED RESPONSE:
>{"url": "/rest/admin/users/1000001", "cause": "NotFoundException", "details": ["Not found entity with id=1000001"]}
<br>

**Get list of all users:**

GET URL: /rest/admin/users/

SUCCESS RESPONSE:
>[{"id": 100004, "name": "Admin2", "email": "admin2@gmail.com", "password": "$2a$10$dcakbnp1euOfDmtvAyXK9.kQQZfaOnEr7NcvQNRU3bqOUYZhqlqIa",
      "roles": ["ROLE_ADMIN", "ROLE_USER"]},
      {"id": 100003, "name": "Admin1", "email": "admin1@gmail.com", "password": "$2a$10$gZKnUn..TzzYJYAF5loXOufg0ZARg1EMzRnjuWs34uSZghjLwBNsO",
      "roles": ["ROLE_ADMIN"]}]
      
FAILED RESPONSE:
>[]
<br>

**Delete user by id:**

DELETE URL: /rest/admin/users/{id}

SUCCESS RESPONSE:
>200

FAILED RESPONSE:
>{"url": "/rest/admin/users/100000", "cause": "NotFoundException", "details": ["Not found entity with id=100000"]}
<br>

**Create/update user:**

POST URL: /rest/admin/users/

BODY: 
>{"name": "New_user", "email": "admin11@gmail.com", "password": "$2a$10$gZKnUn..TzzYJYAF5loXOufg0ZARg1EMzRnjuWs34uSZghjLwBNsO",
"roles": ["ROLE_ADMIN"]}

SUCCESS RESPONSE:
>{"id": 100026, "name": "New_user", "email": "admin11@gmail.com", "password": "$2a$10$gZKnUn..TzzYJYAF5loXOufg0ZARg1EMzRnjuWs34uSZghjLwBNsO",
   "roles": ["ROLE_ADMIN"]}
   
>FAILED RESPONSE:
{"url": "/rest/admin/users/", "cause": "ConstraintViolationException", "details": ["Validation failed for classes ..."]}
<br>

**Get restaurant by id:**

GET URL: /rest/admin/restraunts/{id}

SUCCESS RESPONSE:
>{"id": 100011, "name": "Restraunt1", "updateDate": "2017-07-15", "dishList": null}

FAILED RESPONSE:
>{"url": "/rest/admin/restraunts/1000111", "cause": "NotFoundException", "details": ["Not found entity with id=1000111"]}
<br>

**Delete restaurant by id:**

DELETE URL: /rest/admin/restraunts/{id}

SUCCESS RESPONSE:
>200

FAILED RESPONSE:
>{"url": " /rest/admin/restraunts/100011", "cause": "NotFoundException", "details": ["Not found entity with id=100011"]}
<br>

**Create/update restaurant:**

POST URL: /rest/admin/restraunts/

BODY: 
>{"name": "Restraunt6_new", "updateDate": "2015-05-31", "dishList": null}

SUCCESS RESPONSE:
>{"id": 100028, "name": "Restraunt6_new", "updateDate": "2015-05-31"}

FAILED RESPONSE:
>{"url": "/rest/admin/restraunts/", "cause": "ConstraintViolationException", "details": ["Validation failed for classes ..."]}
<br>

**Get list of all restaurants:**

GET URL: /rest/admin/restraunts/

SUCCESS RESPONSE:
>[{"id": 100028, "name": "Restraunt6_new", "updateDate": "2015-05-31", "dishList": null},
      {"id": 100016, "name": "Restraunt6", "updateDate": "2015-05-31", "dishList": null}]
      
FAILED RESPONSE:
>[]
<br>

**Get list of all restraunts with menu for date:**

GET URL: /rest/admin/restraunts/?date={yyyy-mm-dd}

SUCCESS RESPONSE:
>[{"id": 100012, "name": "Restraunt2", "updateDate": "2017-07-15", "dishList": null},
      {"id": 100011, "name": "Restraunt1", "updateDate": "2017-07-15", "dishList": null}]
      
FAILED RESPONSE:
>[]
<br>

**Get list of restraunts with number of votes by date:**

GET URL: /rest/admin/restraunts/votes/?date={yyyy-mm-dd}

SUCCESS RESPONSE:
>{"100011": 2}

FAILED RESPONSE:
>[]
<br>

**Get dish by id:**

GET URL: /rest/admin/dishes/{id}

SUCCESS RESPONSE:
>{"id": 100010, "name": "JarkoeUpdated", "price": 100, "date": "2015-05-31", "restrauntId": 100013}

FAILED RESPONSE:
>{"url": " /rest/admin/dishes/100006", "cause": "NotFoundException", "details": ["Not found entity with id=100006"]}
<br>

**Get list of dishes by date:**

GET URL: /rest/admin/dishes/?date={yyyy-mm-dd}

SUCCESS RESPONSE:
>[{"id": 100010, "name": "JarkoeUpdated", "price": 100, "date": "2015-05-31"}, {"id": 100009, "name": "Sandwich", "price": 55.5, "date": "2015-05-31"}]

FAILED RESPONSE:
>{"url": "/rest/admin/dishes/", "cause": "NotFoundException", "details": ["No data found per request"]}
<br>

**Get list of dishes by date and restaurant id:**

GET URL: /rest/admin/dishes/?date={yyyy-mm-dd}&restrauntId={id}

SUCCESS RESPONSE:
>[{"id": 100009, "name": "Sandwich", "price": 55.5, "date": "2015-05-31"}, {"id": 100008, "name": "Soup", "price": 50, "date": "2015-05-31"}]

FAILED RESPONSE:
>{"url": "/rest/admin/dishes/", "cause": "NotFoundException", "details": ["No data found per request"]}
<br>

**Delete dish by id:**

DELETE URL: /rest/admin/dishes/{id}

SUCCESS RESPONSE:
>200

FAILED RESPONSE:
>{"url": "/rest/admin/dishes/100000", "cause": "NotFoundException", "details": ["Not found entity with id=100000"]}
<br>

**Create/update dish:**

POST URL: /rest/admin/dishes/

BODY: 
>{"name": "New/updated dish", "price": 100.00, "date": "2015-05-31", "restrauntId": 100013}

SUCCESS RESPONSE:
>{"id": 100026, "name": "New/updated dish ", "price": 100.00, "date": "2015-05-31", "restrauntId": 100013}

FAILED RESPONSE:
>{"url": "/rest/admin/dishes/", "cause": "ConstraintViolationException", "details": ["Validation failed for classes ..."]}
