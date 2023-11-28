-- Insert data into the 'param' table
INSERT INTO param (name, description, value) VALUES
  ('Param1', 'Description for Param1', 10),
  ('Param2', 'Description for Param2', 20),
  ('Param3', 'Description for Param3', 30);

-- Insert data into the 'plant' table
INSERT INTO plant (name, description) VALUES
  ('Plant1', 'Description for Plant1'),
  ('Plant2', 'Description for Plant2'),
  ('Plant3', 'Description for Plant3');

-- Insert data into the 'plantParam' table
INSERT INTO plantParam (plantid, paramid) VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (2, 3),
  (3, 1),
  (3, 3);

-- Insert data into the 'user' table
INSERT INTO user (email, phoneNumber, password) VALUES
  ('user1@example.com', '123-456-7890', 'password1'),
  ('user2@example.com', '987-654-3210', 'password2'),
  ('user3@example.com', NULL, 'password3');

-- Insert data into the 'status' table
INSERT INTO status (description) VALUES
  ('Status1'),
  ('Status2'),
  ('Status3');

-- Insert data into the 'plantstatus' table
INSERT INTO plantstatus (plantid, userid, value) VALUES
  (1, 1, 1),
  (1, 2, 2),
  (2, 2, 3),
  (2, 3, 1),
  (3, 1, 2),
  (3, 3, 3);
