insert into users (username, password)
values ('Ivan Ivanchick', '$2y$10$60DK8JY5dxe9NoRINgDjHuHpiVbfsFsh75OHcACzbvgzjJAnvXLDO'),
       ('Demon2009', '$2y$10$diBIbieBUHB3SsigIT0k..f1Vu/GtXOHswwGckwRaKLusRdHxQvj2');

insert into roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users_roles(user_id, roles_role_id)
values (1, 1),
       (1, 2),
       (2, 2);