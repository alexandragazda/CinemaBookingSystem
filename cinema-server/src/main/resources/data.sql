update showtime
set date=curdate() - interval 2 day
where id=14;

update showtime
set date=curdate() - interval 1 day
where id=15;

update showtime
set date=curdate()
where id>=16 and id<=27;

update showtime
set date=curdate() + interval 1 day
where id>=28 and id<=35;

update showtime
set date=curdate() + interval 2 day
where id>=36 and id<=45;

update showtime
set date=curdate() + interval 3 day
where id>=46 and id<=57;

update showtime
set date=curdate() + interval 4 day
where id>=58 and id<=68;

update showtime
set date=curdate() + interval 5 day
where id>=69 and id<=75;

update showtime
set date=curdate() + interval 6 day
where id>=76 and id<=87;


update movie
set release_date=curdate() + interval 3 day
where id=8;

update movie
set release_date=curdate() + interval 6 day
where id=9;

update movie
set release_date=curdate() + interval 1 day
where id=10;