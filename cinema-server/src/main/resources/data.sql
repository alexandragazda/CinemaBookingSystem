update showtime
set date=curdate() - interval 2 day
where id=12;

update showtime
set date=curdate() - interval 1 day
where id=13;

update showtime
set date=curdate()
where id>=14 and id<=22;

update showtime
set date=curdate() + interval 1 day
where id>=23 and id<=28;

update showtime
set date=curdate() + interval 2 day
where id>=29 and id<=30;

update showtime
set date=curdate() + interval 3 day
where id>=31 and id<=34;

update showtime
set date=curdate() + interval 4 day
where id>=35 and id<=36;

update showtime
set date=curdate() + interval 5 day
where id>=37 and id<=39;

update showtime
set date=curdate() + interval 6 day
where id>=40 and id<=46;

update movie
set release_date=curdate()
where id=4;

update movie
set release_date=curdate() + interval 3 day
where id=6;

update movie
set release_date=curdate() + interval 6 day
where id=7;

update movie
set release_date=curdate() + interval 1 day
where id=8;

update movie
set release_date=curdate() + interval 7 day
where id=10;