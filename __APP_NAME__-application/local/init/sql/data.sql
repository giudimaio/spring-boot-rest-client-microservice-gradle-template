INSERT INTO ROLE(id,name) VALUES(1,'ROLE_ADMIN');
INSERT INTO ROLE(id,name) VALUES(2,'ROLE_USER');
INSERT INTO ROLE(id,name) VALUES(3,'ROLE_MASTER');

INSERT INTO APPLICATION_USER(id,username,password) VALUES(1, 'jdoe', '$2a$10$.V1epoR5h3CTVwM5wfuaHeSqciBgjRenLLPh0KqlcTNLFZIGpjuze');

INSERT INTO APPLICATION_USER_ROLES(application_user_id,roles_id) VALUES(1,3);

INSERT INTO ROLE_HIERARCHY(id,parent_role,child_role) VALUES(1,'ROLE_MASTER','ROLE_ADMIN');
INSERT INTO ROLE_HIERARCHY(id,parent_role,child_role) VALUES(2,'ROLE_ADMIN','ROLE_USER');

INSERT INTO SECURITY_RULE(id,resource_pattern,role) VALUES(1,'/dummies','ADMIN');