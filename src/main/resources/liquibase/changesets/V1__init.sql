create table if not exists users
(
    user_id bigserial primary key,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table if not exists questionnaires
(
    questionnaire_id bigserial primary key,
    user_id bigint not null,
    title varchar(255) not null,
    category varchar(255),
    description varchar(255),
    created_at timestamp(6) with time zone,
    constraint fk_questionnaires_users foreign key (user_id) references users (user_id) on delete cascade on update no action
);

create table if not exists questions
(
    question_id bigserial primary key,
    questionnaire_id bigint not null,
    answer varchar(255) not null,
    task varchar(255) not null unique,
    constraint fk_questions_questionnaires foreign key (questionnaire_id)
        references questionnaires (questionnaire_id)
        on delete cascade on update no action
);

create table if not exists roles
(
    role_id bigserial primary key,
    name varchar(255) not null unique
);

create table if not exists users_roles
(
    user_id bigint not null,
    roles_role_id bigint not null,
    primary key (user_id, roles_role_id),
    constraint fk_users_roles_users foreign key (user_id) references users (user_id) on delete cascade on update no action,
    constraint fk_users_roles_roles foreign key (roles_role_id) references roles (role_id) on delete cascade on update no action
);

