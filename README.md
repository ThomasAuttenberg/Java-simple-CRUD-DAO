<h1>Simple Java CRUD DAO for postgres (or any with the same semantics) database</h1>
<br>Project Files:
<ul>
<li>DataBaseConnection: DataBase connection, executeQuery or execute operations</li>
<li>DataBaseField annotation. Using to mark DAO object fields</li>
<li>DataBaseTable annotation. Using to mark class and point an using database table</li>
<li>DBSerializable interface. Marking interface </li>
<li>DAO<T>: all the logic. CRUD operations above T</li>
</ul>
Example Files:
<ul>
<li>UserDAO: DAO<T> implementation for User class</li>
<li>User</li>
<li>Main</li>
</ul>
<h3> Warning! Make sure you made the resource control above database connections if it's necessary for your project [DAO class] </h3>
