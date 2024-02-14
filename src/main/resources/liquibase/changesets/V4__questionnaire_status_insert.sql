create table if not exists questionnaire_status
(
    questionnaire_status_id bigserial primary key,
    user_id bigint not null,
    questionnaire_id bigint not null,
    status varchar(255) not null,
    correct_answers int,
    incorrect_answers int,
    constraint fk_questionnaire_status_users foreign key (user_id) references users (user_id) on delete cascade on update no action,
    constraint fk_questionnaire_status_questionnaire foreign key (questionnaire_id) references questionnaires (questionnaire_id) on delete cascade on update no action
);