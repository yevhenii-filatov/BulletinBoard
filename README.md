### To run and test the API, follow this instruction: ###

1) Create MySQL database in docker container and configure DB connection in **application.properties**. The easiest way to do that is to install [docker-compose](https://docs.docker.com/compose/install/) and to run this command in terminal:
`sudo docker-compose up -d`.
**docker-compose.yml** is located in the *data* folder of the project repository
2) Run **data.sql** script on the newly created database to insert some info for the testing. Script is located in the same place as **docker-compose.yml**.
3) Run the app and test it via [Postman](https://www.postman.com/downloads/) or any other similar tool.

Application has 3 controllers with the following endpoints list:
1. `/api/auth/`
    - `/signin`- for user logging in (can be accessed by the user with any role)
    - `/signup` - for user registering (can be accessed by the user with any role)
2. `/api/users/`
    - `/delete` - for deleting users from the system (can be accessed by the user with **ADMIN** role)
    - `/update-email` - for updating user's email (can be accessed by the user with **ADMIN** role)
3. `/api/advertisements`
    - `/all` - for viewing all available advertisements (can be accessed by the user with any role)
        - **params**: `page` - page number, `size` - page size, `sorting` - sorting order (`asc`/`desc`)
    - `/details` - for viewing detailed info about one advertisement (can be accessed by the user with any role)
        - **params**: advertisement `id`
    - `/create` - for creation of the advertisement (can be accessed by the user with **ADMIN**, **MODERATOR** or **USER** role)
    - `/delete` - for deleting advertisements (can be accessed by the user with **ADMIN**, **MODERATOR** or **USER** role).
        - **params**: advertisement `id`
