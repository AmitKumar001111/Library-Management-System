# Library-Management-System
This a simple project , its not high level , its only a low level project. this is only backend which is created with the help of Spring Boot.

The Description of this Project is As follows:


 design a library management system:
 features/API:

  Role mgmt:
   1. add/update/delete/list roles    

   Example of roles:
       a. Admin/Librarian
       b. Student
-------------------------------------
role_id  |  role_name 
-------------------------------------
role_001 | admin
role_002 | student
role_003 | librarian



  User mgmt:
  1. add/update/delete/list all users   


  2. get one user (return profile info, in case you have saved user details in multiple collections(address, education details, etc) the merge them and return)


---------------------------------------------------
user_id  |  user_name  | issue_count
---------------------------------------------------
user_001 | amit        | 22
user_002 | bibek       |  3


  User-Role:
  Note: map user role while adding user or create a different collection for user-roles
  1. assign role to specific user   
  2. get all users that have specific role   

---------------------------------------------------
user_role_id  |  user_id  |  role_id  
---------------------------------------------------
user_role_001 |  user_001 |  role_002
user_role_002 |  user_002 |  role_001


User-Address
---------------------------------------------------
user_address_id  |  user_id  |  user_addredd   | type
---------------------------------------------------
user_address_001 |  user_001 |  Jnu. new delhi.| primary
user_address_002 |  user_001 |  uttrakhand     | secondary



  Resources/Books:
  1. Add/Update/Delete/List all Resources/Books
  Some validations must be considered:
      a. only admin user can add/edit/delete/list Resources/Books 
      b. Student can view only his/her borrowed Resources/Books    

------------------------------------------------------
book_id  | book_title  |  author  |  count
------------------------------------------------------
book_001 | psychology  | k.lal    |  21
book_002 | mathematics | r.gupta  |  10



  Student-Resources mapping
  1. Borrow Resources/Books from library
  Some validations must be considered:
    a. Only admin can assign book to student 
    b. Student can take max two Resources/Books, so you must check if that student has taken two books previously 
    c. Only one Resource/Book can be burrowed at a time
    d. total available book count must reduce by one 
    e. if available book count is 0 then it can not be burrowed 

@Postmapping("/issue/issue_book")
issueBook(String librarianId, String studentId, String bookId){
// check the role of the user who is assigning the book/resorce
// check if the role of the librarianId is "Admin"

}

----------------------------------------------------------------------------------------------
transaction_id   |   user_id       |  book_id     |  issue_date  | return_status   | return_date
---------------------------------------------------------------------------------------------
issue_001        |   user_003      |  book_002    | 01/02/2021   |   yes           |  20/01/2021   
issue_002        |   user_002      |  book_003    |
issue_003        |  user_003       |  book_001

  2. Return book
  Some validations must be considered:
    a. Only Admin can get a book from student ------>(test it.)
    b. Only one Resource/Book can be returned at a time  ----> not valid.
    c. total book count must increase by one   -------------> Completed.


