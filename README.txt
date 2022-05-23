----------------------------Computer repair service hierarchy------------------------------
+++USERS table: 
Columns: id; name; email; age; employee_id; user_Phone_Number
-This table contains all the costumers(who owns at least one device -> 1-m relationship), who have a unique phone number (1-m relationship) and employees, who have a unique employee ID (1-1 relationship), can repair many computers (1-m relationship), have a unique phone number (1-m relationship), and can have more than one assigned work shift.

+++Phone_Numbers table:
Columns: id; Phone_Number
-A single phone number can be shared between more than one costumer.

+++Employee_IDs table:
Columns: id; employee_ID(employee identification)
-This table contains the identification of each employee of the Users table (1-1 relationship).

+++Computer_Problems table:
Columns:id, type_Of_Problem; computer_For_Repair_id;
-This table contains the possible cause of the computer problems: software level problems or hardware level problems. A single computer may have at least one of them (1-m relationship).

+++Software_Problems table:
Columns: id, type_Of_Software_Problem:
-A software problem can have many causes (1-m relationship).

+++Hardware_Problems table:
-A hardware problem can have many causes (1-m relationship).

+++Operative_System table:
Columns: id, O_S, version;
-A single O_S can be installed in multiple computers (m-1 relationship).

+++Computers_For_Repair table:
Columns: id; model; user_id(owner); user_id(employee); entry_Date; delivery_date
-This table contains the computers for repair. Each user(owner) can have more than one device for repair (1-m relationship), and each employee can repair more than one computer (1-m relationship).

+++Work_Schudle table:
Columns: id, work_Day, shift
-This table contains the different work shift that the employees can take. One employe can have more than one work shift in the month, while a single work fist (day+shift) can be taken by more the one employee in the month (m-m relationship).

+++User_Has_Work_Schudle:
Columns: id, status, user_id;
-This table contains the diferent work shif each employee take in the month (1-m relationships between the users with an employee ID and the work shifts whit the current table).