 
insert into  Clients VALUES (111, '22XX12345',0, 'Pauline', 'Prevost',  12,  ' rue Condillac',38400, 'grenoble', 'France');
insert into  Clients VALUES  (222, '33XX56789',0, 'Jlassi', 'Imene',  14,  'rue Condillac',38400, 'grenoble', 'France') ;
insert into  Clients VALUES (333, '99XX12345',60, 'Galmami', 'Mohamed',  13,  'rue saint gilles',7500, 'Paris', 'France'); 
insert into  Clients VALUES (444, '44XX12345',0, 'Meunier', 'Maxence',  12,  'rue Martin Luther King',38400, 'Grenoble', 'France'); 
insert into  Clients VALUES (555, '11XX12345',0, 'Pierre', 'Mael',  1,  'rue Marceau',380, 'Grenoble', 'France') ;

       


 
insert into HOTESSES VALUES (01, 0, 'français', 'Martin', 'Jade',  12,  ' rue Condillac',384, 'grenoble', 'France') ;
insert into HOTESSES VALUES (02, 0, 'anglais', 'pierre', 'cholé',  5,  ' rue Berlioz',384, 'grenoble', 'France') ;



insert into ModelesAvions VALUES ('A300B1', 11, 22);
insert into ModelesAvions VALUES ('DC-1', 10, 22); 
insert into ModelesAvions VALUES('DC-10', 12, 22);
insert into ModelesAvions VALUES ('DH.114', 15, 22); 
insert into ModelesAvions VALUES ('DH.91', 20, 22) ;
insert into ModelesAvions VALUES('A300', 30, 29) ;

 
insert into Pilotes VALUES  (001, 0, 'françois', 'françois',  1,  ' rue Sebastien gryphe',690, 'Lyon', 'France'); 
insert into Pilotes VALUES (002, 0, 'Olive', 'Olivier',  10,  'rue new york ',380, 'grenoble', 'France');
insert into Pilotes VALUES(003, 0, 'Navet', 'Paul',  3,  'rue Arago',380, 'Grenoble', 'France');
insert into Pilotes VALUES (004, 0, 'Durand', 'Alexandre',  9,  'rue Martin Luther King',384, 'Grenoble', 'France');
insert into Pilotes VALUES (005, 0, 'David', 'David',  5,  'rue Marceau',380, 'Grenoble', 'France') ;



 
insert into  Avions  VALUES (11, 'A300',100,5,5) ;
insert into  Avions  VALUES (22, 'DH.91',141,6,6) ;
insert into  Avions  VALUES (33, 'DH.114',30,10,10) ;
insert into  Avions  VALUES (44, 'A300B1',90,20,20) ;
insert into  Avions  VALUES (55, 'DC-10',100,30,20);

insert into Places VALUES(1,11,'Centre','Eco',50);
insert into Places VALUES (2,11,'Couloir','Première',700);
insert into Places VALUES(3,22,'Centre','Eco',50);
insert into Places VALUES (4,22,'Hublot','Première',700);	

insert into Vols VALUES (111,11,'LyonStE','TunisCart','16:15',to_date('2020-04-16','YYYY-MM-DD'),'1:55',1359,80,3,4);
insert into Vols VALUES (112,22,'LyonStE','Casablanca','15:15',to_date('2020-04-19','YYYY-MM-DD'),'2:15',1541,100,5,5);


insert into Qualifie VALUES('A300',001);
insert into Qualifie VALUES('A300',002);
insert into Qualifie VALUES('DH.91',001);
insert into Qualifie VALUES('A300B1',001);

insert into Reservations VALUES (1111,2,111,222,to_date('2020-04-10','YYYY-MM-DD'),250);

insert into Reservations VALUES (1112,3,112,111,to_date('2020-04-05','YYYY-MM-DD'),1200);
	



















 

