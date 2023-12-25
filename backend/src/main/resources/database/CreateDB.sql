
-- create
CREATE TABLE IF NOT EXISTS param
(
  paramid INTEGER PRIMARY KEY AUTO_INCREMENT,
  name TEXT NOT NULL,
  description TEXT NOT NULL,
  value INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS plant
(
    plantid INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS bucket
(
    bucketId INTEGER PRIMARY KEY AUTO_INCREMENT,
    plantid INTEGER,
    name INTEGER,
    FOREIGN KEY (plantid)  REFERENCES plant (plantid)
);

CREATE TABLE IF NOT EXISTS plantParam
(
    plantParamid INTEGER PRIMARY KEY AUTO_INCREMENT,
    plantid INTEGER,
    paramid INTEGER,
    FOREIGN KEY (plantid)  REFERENCES plant (plantid),
    FOREIGN KEY (paramid)  REFERENCES param (paramid)

);

-- create
CREATE TABLE IF NOT EXISTS user1
(
  userid INTEGER PRIMARY KEY AUTO_INCREMENT,
  email TEXT NOT NULL,
  phoneNumber TEXT,
  password TEXT NOT NULL
);

create TABLE IF NOT EXISTS status(
statusid INTEGER PRIMARY KEY AUTO_INCREMENT,
description TEXT NOT NULL
);
-- create
CREATE TABLE IF NOT EXISTS plantstatus
(
  plantstatusid INTEGER PRIMARY KEY AUTO_INCREMENT,
  plantid INTEGER,
  userid INTEGER,
  value INTEGER,
   FOREIGN KEY (plantid)  REFERENCES plant (plantid),
  FOREIGN KEY (userid)  REFERENCES user1 (userid),
  FOREIGN KEY (value)  REFERENCES status (statusid)
);

