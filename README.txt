------------------------------------Computer repair service tables hierarchy------------------------------------

+++Users table: 
Columns: id; name; email; age; employee_id; user_Phone_Number
-This table contains all the costumers(who owns at least one device -> 1-m relationship), who have a unique phone number (1-m relationship) and employees, who have a unique employee ID (1-1 relationship), can repair many computers (1-m relationship), have a unique phone number (1-m relationship), and can have more than one assigned work shift.

+++Phone_Numbers table:
Columns: id; Phone_Number
-A single phone number can be shared between more than one costumer.

+++Adresses table:
Columns: id; Phone_Number
-A single address can be shared between more than one costumer.

+++Employee_IDs table:
Columns: id; employee_ID(employee identification), user_id;
-This table contains the identification of each employee of the Users table (1-1 relationship).

++Computers:
Columns: id, model, year, operating_system_id, processor_id, data_storage_device_type_id, data_storage_device_type_capacity;
This table contains different models of computers.

+++Operating_Systems table:
Columns: id, O_S, version;
-A single O_S can be installed in multiple computers (m-1 relationship).

+++Procesors table:
Columns: id, O_S, model;
-A single processor model can be part of multiple computers (m-1 relationship).

++Data_Storage_Devices_type:
Columns: id, data_storage_device_type;
Many computers may share the same data storage device type, like HDD or SSD. (1-m relationship).

+++Computer_Problem_Types table:
Columns:id, problem_type;
-This table contains the possible cause of the computer problems: software level problems or hardware level problems. A single computer for repair may have at least one of them (1-m relationship).

+++Computer_Problem_Sub_Types table:
Columns: id, type_Of_Software_Problem:
-This table contains different types of software and hardware problems that are well known and the average price of the repair service of this issues. Many sub-type problems can share the same software or hardware main problem. (1-m relationship).

+++Computers_For_Repair table:
Columns: id; model; user_id(owner); repairer_id; entry_Date; delivery_date
-This table contains the computers for repair. Each user(owner) can have more than one device for repair (1-m relationship), and each employee can repair more than one computer (1-m relationship).

++Computers_For_Rapair_Diagnosis:
Columns: id, computer_for_rapair_id, computer_problem_sub_type_id;
Since computer_for_repair and computer_problem_sub_types tables have a m-m relationship, this third intermediate table stores the information of all the problems that have been diagnosed for each computer for repair.

+++Work_Schudle table:
Columns: id, work_Day, shift
-This table contains the different work shift that the employees can take. One employe can have more than one work shift in the month, while a single work fist (day+shift) can be taken by more the one employee in the month (m-m relationship).

+++Employee_Work_Schudle:
Columns: id, user_id, work_schedule;
-This table contains the different working shifts each employee take in the month (1-m relationships between the users with an employee ID and the work shifts whit the current table).
