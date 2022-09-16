# tappter-api
Quick guide to the api use:
1. Developed using Spring Tool Suite 4
2. A .jar file called an-api-for-tappter.jar is contained in the target folder.
3. I tested the endpoints through Postman
4. There are two roles available. The details are hardcoded inside the code:<br />
	ADMIN role: 	username = admin<br />
			password = admin-password<br />
	GUEST role:	username = guest<br />
			password = guest-password<br />
This credentials have to be inserted in the Auth section(Basic Auth) when sending a request through Postman.<br />
5. admin has access to the following endpoints:<br />
	GET - http://localhost:8080/api/v1/users/user/{personId}<br />
	GET - http://localhost:8080/api/v1/users/{offset}/{pageSize}<br />
	POST - http://localhost:8080/api/v1/users<br />
	DELETE - http://localhost:8080/api/v1/users/delete/{personId}<br />
	PUT - http://localhost:8080/api/v1/users/update/{personId}<br />
	GET - http://localhost:8080/api/v1/users/nameandage/?name=maria&age=21<br />
6. guest has access to the following endpoint<br />
	GET - http://localhost:8080/api/v1/users/nameandage/?name=maria&age=21<br />
7. The endpoint http://localhost:8080/api/v1/users/nameandage/?name=maria&age=21 must contain both the name and the age parameters. The name can be partial as the point will filter all the name as requested.
8. Test coverage is around 70%
