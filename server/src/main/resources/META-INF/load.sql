insert into player(id, name, user) values (1, 'Rafael', 'rfaita@gmail.com');
insert into player(id, name, user) values (2, 'João', 'joao@gmail.com');

insert into hero (id, herotypeid, level, idplayer) values (1, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (2, '91b9cddc-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (3, '91b9d174-3fbe-11e7-a919-92ebcb67fe33',1,2);
insert into hero (id, herotypeid, level, idplayer) values (4, '91b9d386-3fbe-11e7-a919-92ebcb67fe33',1,2);
insert into hero (id, herotypeid, level, idplayer) values (5, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,1);
insert into hero (id, herotypeid, level, idplayer) values (6, '91b9d066-3fbe-11e7-a919-92ebcb67fe33',1,2);

insert into formation(id, idplayer, formationAllocation) values (1, 1, 0);
insert into formation(id, idplayer, formationAllocation) values (2, 2, 1);

insert into positionedhero(id, idhero, battlePosition, idformation) values (1, 1, 0, 1);
insert into positionedhero(id, idhero, battlePosition, idformation) values (2, 2, 1, 1);
insert into positionedhero(id, idhero, battlePosition, idformation) values (5, 5, 2, 1);

insert into positionedhero(id, idhero, battlePosition, idformation) values (3, 3, 0, 2);
insert into positionedhero(id, idhero, battlePosition, idformation) values (4, 4, 1, 2);
insert into positionedhero(id, idhero, battlePosition, idformation) values (6, 6, 2, 2);

