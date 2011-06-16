-- error throwing
-- my post on h2 asking for more suggestions...
-- http://groups.google.com/group/h2-database/browse_thread/thread/25a9873d6b9405fc

-- my error function
drop alias if exists throwErrorIf;
create alias throwErrorIf AS 
'void throwErrorIf(Boolean condition,String msg) throws java.sql.SQLException {if (condition) throw new java.sql.SQLException(msg);}';

-- basic tests
call throwErrorIf(false, 'some message');
call throwErrorIf(true, 'some message'); -- will throw exception

-- proper test
drop table if exists norows;
create table norows (id int primary key);
select throwErrorIf(count(*) > 0, 'should be no rows') from NOROWS;
insert into norows values(1);
select throwErrorIf(count(*) > 0, 'should be no rows') from NOROWS; -- will throw exception

