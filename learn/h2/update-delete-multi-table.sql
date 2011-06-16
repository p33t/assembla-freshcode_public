-- From my post: 
-- http://groups.google.com/group/h2-database/browse_thread/thread/ed2e5360d5102ba8/5a33e5d34fc7b804

-- SETUP
drop table if exists t1, t2;
create table t1 (id int PRIMARY KEY, flag boolean); 
insert into t1(id) values(1), (2); 
create table t2 (id int PRIMARY KEY); 
insert into t2 values(1);

-- UPDATE
-- slow
update t1 set flag = true where id in (select id from t2); 
-- fast but has side effect of setting non-joining records to 'null'<<<<<<<<<<<<<<<<<<<<<<<
update t1 set flag = (select true from t2 where t2.id = t1.id);
-- where <some clause to reduce side effect>

-- DELETE
-- slow
delete from t1 where id in (select id from t2); 
-- fast <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
delete from t1 where (select true from t2 where t2.id = t1.id); 