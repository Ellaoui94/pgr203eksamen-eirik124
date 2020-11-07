create table projectmember_to_project (
    id serial primary key,
    project_name int not null,
    projectmember_name varchar(100) not null,
    task_name int not null,
    description varchar(500),
    status varchar(100)
);