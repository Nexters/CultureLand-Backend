insert into user(seq, user_id, created_by) values(null, 1234567, now());
insert into diary (id, content, created_by, place, sometime, title, user_seq, with_who)
            values (null, 'content', now(), 'place', now(), 'title', 1, 'withwho');