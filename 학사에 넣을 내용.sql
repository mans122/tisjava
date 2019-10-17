    select year, month, count(*) count
    from(select substr(br.rdate,0,4) year,substr(br.rdate,5,2) month from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no)
      group by year,month order by count desc;


select substr(br.rdate,0,4) year,substr(br.rdate,5,2) month from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no;

select * from bookRent;

insert into bookRent values ('2019100901','0494513','000002','20191009');
insert into bookRent values ('2019100902','1091011','000001','20191009');
insert into bookRent values ('2019100903','0792012','000003','20191009');

insert into bookRent values ('2019100901','0494513','000003','20191009');
insert into bookRent values ('2018081001','0792012','000001','20180810');
insert into bookRent values ('2018081002','0494513','000003','20180810');
insert into bookRent values ('2018081003','1091011','000002','20180810');
insert into bookRent values ('2018081004','0792012','000003','20180810');

insert into bookRent values ('2018081004','0792012','000003','20180810');

select dept, count(*) as count 
from (select s.dept, br.rdate
from student s, books b, bookRent br
where br.id=s.id
and br.bookNo=b.no)
group by dept order by count desc;


select * from bookRent;
select * from books;

create table bookReturn
( no char(10) primary key, 
  id char(10) not null, 
  bookNo char(6) not null, 
  rDate char(8) not null
);

select * from bookReturn;

insert into bookReturn values('2019101001','1091011','000002','20191010');
insert into bookReturn values('2019101002','1091011','000001','20191010');
insert into bookReturn values('2019101003','7092012','000003','20191010');
insert into bookReturn values('2019101004','0792012','000003','20191010');


--반납 안된 책들
select substr(aa.no,1,4) year,substr(aa.no,5,2) month,substr(aa.no,7,2) day,aa.id, aa.bookno bn,bs.title
from  books bs,   
    (select DISTINCT b.no no,b.id id,b.bookno bookno,a.bn bn from bookRent b,
    (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
    where b.id = a.id(+) and b.bookno = a.bn(+)) aa
where bn is null and bs.no = aa.bookno order by aa.no;


--------------------------------------
select aa.*,bs.title title
from  books bs,   
    (select DISTINCT b.no no,b.id id,b.bookno bookno,a.bn bn from bookRent b,
    (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
    where b.id = a.id(+) and b.bookno = a.bn(+)) aa
where bn is null and bs.no = aa.bookno order by aa.no;

select DISTINCT b.no no,b.id id,b.bookno bookno,a.bn bn from bookRent b,
    (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
    where b.id = a.id(+) and b.bookno = a.bn(+);
    
    select DISTINCT *
    from bookRent b,
    (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
    where b.id = a.id(+)
    and b.bookno = a.bn(+);
    --BookRent 테이블에서 반납된걸 제외한 값을 찾는쿼리
    select DISTINCT b.no no,b.id id,b.bookno bookno,a.bn bn
    from bookRent b,
    (select br.id id, br.bookno bn  from bookRent br,bookReturn rb where br.id=rb.id and br.bookno = rb.bookno) a
    where b.id = a.id(+)
    and b.bookno = a.bn(+);

select * from bookRent;

select * from bookReturn;

create table bookRent
( no char(10) primary key, -- 대여번호
  id char(10) not null, -- 학번
  bookNo char(6) not null, -- 책번호
  rDate char(8) not null -- 대여일
  
);
drop table bookrent2;
create table bookRent2(
    rentNo char(11) primary key,
    bookNo char(6) not null,
    id char(7) not null,
    rDate char(8) not null,
    returnDate char(8) null
);

insert into bookRent2 values('20191013001','000001','1111111','20191013',null);
insert into bookRent2 values('20191014001','000003','1354651','20191014',null);
insert into bookRent2 values('20191010001','000001','1354651','20191010',null);
insert into bookRent2 values('20190910001','000003','4456754','20190910',null);
insert into bookRent2 values('20180710001','000002','1091011','20190910',null);
commit;
select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate
from books b,
(select rentno,bookno,id,rdate,returndate from bookRent2) br
where b.no = br.bookno;

select * from bookRent2;
update bookrent2 set returndate='20191014' where rentno='20191013001';
commit;

select * from books;
create table books2(
    no char(6) PRIMARY key,
    title varchar2(50) not null,
    writer varchar2(20) not null,
    author varchar2(50) null
);

insert into books2(no,title,writer) select * from books;
select * from books2;
--미반납한 사람 찾는 쿼리
select * from bookrent2 where returndate is not null;

delete from bookrent2 where rentno='20191015001';
commit;

select s.id, s.name, b.title, br.rDate
				 from student s, books b, bookRent br
				 where br.id=s.id
				 and br.bookNo=b.no;
                 
select br.rentno rn,b.title title, br.bookno bn, s.id id, s.name name, s.dept dept, br.rdate rd from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no and dept='생활체육' order by rn;
select *from bookRent2;

delete from bookrent2 where rentno='20191015001';
					select br.rentno rn,b.title title, br.bookno bn, s.id id, s.name name, s.dept dept, br.rdate rd, br.returndate
							 from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no and br.returndate is null;
                             
select *	 from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no;

select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate
            from books b, (select * from bookRent2) br where b.no = br.bookno order by rentno;
            
select br.rentno rn,b.title title, br.bookno bn, s.id id, s.name name, s.dept dept, br.rdate rd 
						 from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no;
            
select br.rentno rn,b.title title, b.no no, b.author, br.id id, br.rdate rdate, br.returndate redate 
                         from books b, (select * from bookRent2) br where b.no = br.bookno and br.returndate is null order by rn;
                         
select br.rentno rn,b.title title, br.bookno bn, s.id id, s.name name, s.dept dept, br.rdate rd, br.returndate
 from student s, books2 b, bookrent2 br where br.id = s.id and br.bookno = b.no and br.returndate is null order by rn;
 
 
 --bookgraph
 select year, month, count(*) count from(select substr(br.rdate,0,4) year,substr(br.rdate,5,2) month from student s, books b, bookRent br where br.id=s.id and br.bookNo=b.no)
						 group by year,month order by count desc;
                         
select dept, count(*) as count from (select s.dept, br.rdate from student s, books2 b, bookRent2 br
						 where br.id=s.id and br.bookNo=b.no) group by dept order by count desc;                         
                         
select title, count(*) count from(select br.id id,name,b.no no,b.title title, br.rdate from student s,
						 books2 b, bookRent2 br where br.id=s.id and br.bookNo=b.no) group by title order by count desc;
                         
select year, month, count(*) count from(select substr(br.rentno,0,4) year,substr(br.rentno,5,2) month from student s, books2 b, bookRent2 br where br.id=s.id(+) and br.bookNo=b.no)
						 group by year,month order by count desc;
                         
select * from bookrent2;
select * from books2;
select * from student;

create table student(
    id char(7) primary key,
    name varchar2(20) not null,
    dept varchar2(20) not null,
    address varchar2(100) null,
    birth char(6) default '000000' not null
);
drop table student;

select * from student2;
delete from student2;

insert into student2(id,name,dept,address,birth) select * from student;
insert into student2(id,name,dept,address) select * from student;
insert into student(id,name,dept,address,birth) select * from student2;
select * from student;

update student2 set birth='940829' where id='1111111';
commit;

select count(*) as count from student where id='1111111';
commit;

update bookrent2 set bookno='000007' where rentno='20191014004';