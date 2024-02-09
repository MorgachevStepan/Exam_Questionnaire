insert into users (username, password)
values ('Admin', '$2y$10$06FlDAJkw4hFQ5Wj500BPuFbIPjwfdrI1PESN/xvj1T9SU9VtnEJK'),
       ('Ivan', '$2y$10$aHWVdEVohb7EH9qpXHLJauprSxdPKaABjkhKSoegZTmkv7puVmaRC');

insert into roles(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users_roles(user_id, roles_role_id)
values (1, 1),
       (1, 2),
       (2, 2);