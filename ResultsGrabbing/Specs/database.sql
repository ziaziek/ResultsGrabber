create sequence players_id;

create table players(id integer default nextval('players_id') primary key,
			first_name varchar(25),
			last_name varchar(30),
			birthday date,
			country varchar(50)
			);

create sequence matches_id;

create table matches(id integer default nextval('matches_id') primary key,
			match_date date,
			city varchar(50),
			country varchar(50),
			unique(match_date, city, country)
			);

create sequence games_id;

create table games(id integer default nextval('games_id') primary key,
		idmatches integer references matches(id),
		idplayers integer references players(id),
		idoponents integer references players(id),
		age integer,
		oponentrank integer,
		avgpointdiff double precision
		);

create sequence player_games_time_id;

create table playergamestime(id integer default nextval('player_games_time_id') primary key,
				idplayers integer references players(id),
				idgames integer references games(id), 
				maxranksofar integer check (maxranksofar>0),
				minranksofar integer check (minranksofar>0),
				currentrank integer check (currentrank>0)
				);