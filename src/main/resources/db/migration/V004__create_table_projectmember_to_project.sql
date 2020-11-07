create table projectmember_to_project (
    id serial primary key,
    project_name int not null,
    projectmember_name int not null,
    task_name int not null,
    description varchar(500),
    status varchar(100),
    FOREIGN KEY (project_name) REFERENCES project(id),
    FOREIGN KEY (projectmember_name) REFERENCES projectmembers(id),
    FOREIGN KEY (task_name) REFERENCES task(id)
);