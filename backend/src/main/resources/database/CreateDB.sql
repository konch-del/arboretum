
-- create
CREATE TABLE param
(
  paramid INTEGER PRIMARY KEY AUTO_INCREMENT,
  name TEXT NOT NULL,
  description TEXT NOT NULL,
  value INTEGER NOT NULL
);

CREATE TABLE plant
(
    plantid INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE plantParam
(
    plantParamid INTEGER PRIMARY KEY AUTO_INCREMENT,
    plantid INTEGER,
    paramid INTEGER,
    FOREIGN KEY (plantid)  REFERENCES plant (plantid),
    FOREIGN KEY (paramid)  REFERENCES param (paramid)

);

-- create
CREATE TABLE user
(
  userid INTEGER PRIMARY KEY AUTO_INCREMENT,
  email TEXT NOT NULL,
  phoneNumber TEXT,
  password TEXT NOT NULL
);

create TABLE status(
statusid INTEGER PRIMARY KEY AUTO_INCREMENT,
description TEXT NOT NULL
);
-- create
CREATE TABLE plantstatus
(
  plantstatusid INTEGER PRIMARY KEY AUTO_INCREMENT,
  plantid INTEGER,
  userid INTEGER,
  value INTEGER,
   FOREIGN KEY (plantid)  REFERENCES plant (plantid),
  FOREIGN KEY (userid)  REFERENCES user (userid),
  FOREIGN KEY (value)  REFERENCES status (statusid)
);

