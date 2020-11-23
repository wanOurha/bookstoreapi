# bookstoreapi


bookstore api deploy on https://bookstoreapisp.herokuapp.com


-GET:https://bookstoreapisp.herokuapp.com/books               get all book with recommended first in list 

-GET:https://bookstoreapisp.herokuapp.com/users               (Login required)  get users info and orders history

-POST:https://bookstoreapisp.herokuapp.com/login              Login to system and get token (bearer token)

-POST:https://bookstoreapisp.herokuapp.com/users              register user to system
  
-POST:https://bookstoreapisp.herokuapp.com/users/oders        (Login required)  add orders detail and return sum price

-DELETE:https://bookstoreapisp.herokuapp.com/users            (Login required)  delete user detail and user orders history from DB


 (Login required) are use token from login to authorized and use it in authorization header Bearer token type
