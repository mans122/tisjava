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