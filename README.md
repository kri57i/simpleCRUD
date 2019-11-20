# simpleCRUD
A simple crud application using SpringBoot, MySQL, Hibernate, JPA

This CRUD application is based on a database schema, consisting of 5 interconnected entities.
The following logic stands for each entity: 1)Model(which contains the properties of the entities that will be saved as an actual
database table, holding the values as columns, also matching with other table's properties(mainly primary keys).
2)Repository(containing Jpa default methods and custom methods)that will be applied on entities,
3)Service(applying actions over entities: create, read, update or terminate(by setting an actual value to the to_date fields) 
in other words: the application logic).
4)Controller(containing url's that redirects to a specific part of the application 
which invokes a specific method from the service)

JUnit tests are also applied on each repository of each model, mainly testing the repository by saving an actual object(using 
TestEntityManager, which is an interface that temporarly saves and removes an instance of an entity(in an in-memory database))
and also testing the size of the actual repository, which is expected to be greater than zero.
