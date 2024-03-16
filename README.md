<h>Simple Java CRUD DAO for postgres (or any with the same semantics) database</h>
Project Files:
<ul>
<li>DataBaseConnection: DataBase connection, executeQuery or execute operations</li>
<li>DataBaseField annotation. Using to mark DAO object fields</li>
<li>DataBaseTable annotation. Using to mark class and point an using database table</li>
<li>DAO<T>: all the logic. CRUD operations above T</li>
</ul>
Example Files:
<ul>
<li>UserDAO: DAO<T> implementation for User class</li>
<li>User</li>
<li>Main</li>
</ul>
