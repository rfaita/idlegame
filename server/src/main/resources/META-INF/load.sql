insert into formation(id, idplayer, formationAllocation) values (10, null, 0);
insert into formation(id, idplayer, formationAllocation, idnextlevelformation) values (20, null, 0, 10);
insert into formation(id, idplayer, formationAllocation, idnextlevelformation) values (30, null, 0, 20);

insert into player(id, name, user, linkedUser, idnextlevelformationpve) values (1, 'Rafael', 'rfaita@gmail.com', '0030d3c8-80b1-4eb9-af33-4f9fd82c1911', 30);
insert into player(id, name, user, linkedUser) values (2, 'João', 'joao@gmail.com', null);

insert into hero (id, herotypeid, level, idplayer) values (1, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (2, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (3, '91b9d174-3fbe-11e7-a919-92ebcb67fe33',1,2);
insert into hero (id, herotypeid, level, idplayer) values (4, '91b9d386-3fbe-11e7-a919-92ebcb67fe33',1,2);
insert into hero (id, herotypeid, level, idplayer) values (5, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (6, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,2);

insert into formation(id, idplayer, formationAllocation) values (1, 1, 0);
insert into formation(id, idplayer, formationAllocation) values (3, 1, 3);
insert into formation(id, idplayer, formationAllocation) values (2, 2, 1);

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