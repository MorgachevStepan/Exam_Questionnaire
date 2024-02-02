insert into questionnaires (user_id, title, category, description, created_at)
values (1, 'Maths test', 'Math', 'This test contains some questions about math', '2024-01-29 12:00:00'),
       (1, 'Geom test', 'Math', 'This test contains some questions about geom',  '2024-01-31 00:00:00'),
       (2, 'Test', 'History', 'Hello from 2009!!!', '2024-02-1 00:00:00');

insert into questions (questionnaire_id, answer, task)
values (1, '5', '2 + 3 = ?'),
       (1, '10', '5 * 2 = ?'),
       (1, '1', '0! = ?'),
       (2, 'Test Geom answer', 'Test geom task'),
       (2, 'Test Geom answer 2', 'Test geom task2'),
       (3, 'Test', 'Test');