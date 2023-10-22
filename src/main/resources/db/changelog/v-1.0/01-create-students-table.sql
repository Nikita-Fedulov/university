create table students (
                                id bigint not null auto_increment,
                                fullname varchar(255) not null,
                                admission_date date not null,
                                group_id bigint,
                                primary key (id)
) engine=InnoDB


--
-- alter table students
--         foreign key (group_id)
--             references university_groups (id);
