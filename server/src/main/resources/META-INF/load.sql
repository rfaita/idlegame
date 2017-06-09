insert into formation(id, idplayer, formationAllocation) values (10, null, 0);
insert into formation(id, idplayer, formationAllocation, idnextlevelformation) values (20, null, 0, 10);
insert into formation(id, idplayer, formationAllocation, idnextlevelformation) values (30, null, 0, 20);

insert into player(id, name, pvpscore, linkeduser) values (10, 'teste1',23, '1123');
insert into player(id, name, pvpscore, linkeduser) values (11, 'teste3',123, '1123');
insert into player(id, name, pvpscore, linkeduser) values (12,'teste4', 223, '1123');
insert into player(id, name, pvpscore, linkeduser) values (13,'teste5', 243, '1123');
insert into player(id, name, pvpscore, linkeduser) values (14,'teste6', 3123, '1123');
insert into player(id, name, pvpscore, linkeduser) values (15,'teste7', 3113, '1123');
insert into player(id, name, pvpscore, linkeduser) values (16,'teste8', 2123, '1123');
insert into player(id, name, pvpscore, linkeduser) values (17,'teste9', 4123, '1123');
insert into player(id, name, pvpscore, linkeduser) values (18,'teste10', 123, '1123');
insert into player(id, name, pvpscore, linkeduser) values (19,'teste11', 1123, '1123');

insert into player(id, pvpscore,name, user, linkedUser, level, idnextlevelformationpve, gold, ancientrune, soul, spiritcrystal, goldpersecond, ancientrunepersecond, soulpersecond, spiritcrystalpersecond) values (1, 1000,'Rafael', 'rfaita@gmail.com', '0030d3c8-80b1-4eb9-af33-4f9fd82c1911', 10, 30, 0,0,0,100, 100, 10, 10, 0);
insert into player(id, pvpscore,name, user, linkedUser) values (2, 123, 'João', 'joao@gmail.com', null);

insert into hero (id, herotypeid, level, idplayer) values (1, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (2, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (3, '91b9d174-3fbe-11e7-a919-92ebcb67fe33',1,2);
insert into hero (id, herotypeid, level, idplayer) values (4, '91b9d386-3fbe-11e7-a919-92ebcb67fe33',1,2);
insert into hero (id, herotypeid, level, idplayer) values (5, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (6, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,2);


insert into hero (id, herotypeid, level, idplayer) values (200, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,10);
insert into hero (id, herotypeid, level, idplayer) values (201, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,11);
insert into hero (id, herotypeid, level, idplayer) values (202, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,12);
insert into hero (id, herotypeid, level, idplayer) values (203, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,13);
insert into hero (id, herotypeid, level, idplayer) values (204, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,14);
insert into hero (id, herotypeid, level, idplayer) values (205, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,15);
insert into hero (id, herotypeid, level, idplayer) values (206, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,16);
insert into hero (id, herotypeid, level, idplayer) values (207, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,17);
insert into hero (id, herotypeid, level, idplayer) values (208, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,18);
insert into hero (id, herotypeid, level, idplayer) values (209, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,19);


insert into formation(id, idplayer, formationAllocation) values (100, 10, 0);
insert into formation(id, idplayer, formationAllocation) values (101, 11, 0);
insert into formation(id, idplayer, formationAllocation) values (102, 12, 0);
insert into formation(id, idplayer, formationAllocation) values (103, 13, 0);
insert into formation(id, idplayer, formationAllocation) values (104, 14, 0);
insert into formation(id, idplayer, formationAllocation) values (105, 15, 0);
insert into formation(id, idplayer, formationAllocation) values (106, 16, 0);
insert into formation(id, idplayer, formationAllocation) values (107, 17, 0);
insert into formation(id, idplayer, formationAllocation) values (108, 18, 0);
insert into formation(id, idplayer, formationAllocation) values (109, 19, 0);

insert into formation(id, idplayer, formationAllocation) values (1, 1, 0);
insert into formation(id, idplayer, formationAllocation) values (3, 1, 3);
insert into formation(id, idplayer, formationAllocation) values (2, 2, 1);


insert into positionedhero(id, idhero, battlePosition, idformation) values (300, 200, 0, 100);
insert into positionedhero(id, idhero, battlePosition, idformation) values (301, 201, 0, 101);
insert into positionedhero(id, idhero, battlePosition, idformation) values (302, 202, 0, 102);
insert into positionedhero(id, idhero, battlePosition, idformation) values (303, 203, 0, 103);
insert into positionedhero(id, idhero, battlePosition, idformation) values (304, 204, 0, 104);
insert into positionedhero(id, idhero, battlePosition, idformation) values (305, 205, 0, 105);
insert into positionedhero(id, idhero, battlePosition, idformation) values (306, 206, 0, 106);
insert into positionedhero(id, idhero, battlePosition, idformation) values (307, 207, 0, 107);
insert into positionedhero(id, idhero, battlePosition, idformation) values (308, 208, 0, 108);
insert into positionedhero(id, idhero, battlePosition, idformation) values (309, 209, 0, 109);

insert into positionedhero(id, idhero, battlePosition, idformation) values (1, 1, 0, 1);
insert into positionedhero(id, idhero, battlePosition, idformation) values (2, 2, 1, 1);
insert into positionedhero(id, idhero, battlePosition, idformation) values (5, 5, 2, 1);

insert into positionedhero(id, idhero, battlePosition, idformation) values (110, 1, 0, 3);
insert into positionedhero(id, idhero, battlePosition, idformation) values (112, 2, 1, 3);
insert into positionedhero(id, idhero, battlePosition, idformation) values (115, 5, 2, 3);

insert into positionedhero(id, idhero, battlePosition, idformation) values (3, 3, 0, 2);
insert into positionedhero(id, idhero, battlePosition, idformation) values (4, 4, 1, 2);
insert into positionedhero(id, idhero, battlePosition, idformation) values (6, 6, 2, 2);

insert into hero (id, herotypeid, level, idplayer) values (10, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (11, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (12, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (13, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (14, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (15, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);



insert into positionedhero(id, idhero, battlePosition, idformation) values (10, 10, 0, 10);
insert into positionedhero(id, idhero, battlePosition, idformation) values (11, 11, 1, 10);
insert into positionedhero(id, idhero, battlePosition, idformation) values (12, 12, 2, 10);
insert into positionedhero(id, idhero, battlePosition, idformation) values (13, 13, 3, 10);
insert into positionedhero(id, idhero, battlePosition, idformation) values (14, 14, 4, 10);
insert into positionedhero(id, idhero, battlePosition, idformation) values (15, 15, 5, 10);


insert into hero (id, herotypeid, level, idplayer) values (20, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (21, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (22, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (23, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (24, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);
insert into hero (id, herotypeid, level, idplayer) values (25, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',10,null);


insert into positionedhero(id, idhero, battlePosition, idformation) values (20, 20, 0, 20);
insert into positionedhero(id, idhero, battlePosition, idformation) values (21, 21, 1, 20);
insert into positionedhero(id, idhero, battlePosition, idformation) values (22, 22, 2, 20);
insert into positionedhero(id, idhero, battlePosition, idformation) values (23, 23, 3, 20);
insert into positionedhero(id, idhero, battlePosition, idformation) values (24, 24, 4, 20);
insert into positionedhero(id, idhero, battlePosition, idformation) values (25, 25, 5, 20);


insert into hero (id, herotypeid, level, idplayer) values (30, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,null);
insert into positionedhero(id, idhero, battlePosition, idformation) values (30, 30, 0, 30);