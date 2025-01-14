DROP TABLE IF EXISTS Usernames;

CREATE TABLE Usernames (
  id LONG PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL--,
);

DROP TABLE IF EXISTS Questions;

CREATE TABLE Questions (
  id LONG PRIMARY KEY AUTO_INCREMENT,
  option1 VARCHAR(255) NOT NULL,
  option2 VARCHAR(255) NOT NULL,
  option1_id VARCHAR,
  option2_id VARCHAR
);

INSERT INTO Questions (option1, option2, option1_id, option2_id) VALUES
    ('live forever', 'die now', '1a', '1b'),
    ('work a boring job with the same people you like forever',
        'work at a different job every month', '2a', '2b'),
    ('date bad people forever',
        'be with one person that fits you right now for the rest of your life', '3a', '3b'),
    ('go back to your parents','be homeless', '4a', '4b'),
    ('be homeless','a sex worker', '5a', '5b'),
    ('hang a picture of hitler in your room forever',
        'kiss stalin (with tongue)', '6a', '6b'),
    ('jump off a bridge','do the next 9/11', '7a', '7b'),
    ('never eat candy again',
        'eat nothing but candy for the rest of your life', '8a', '8b'),
    ('move to a desert island',
        'a one-bedroom apartment with 10 roommates', '9a', '9b'),
    ('marry & move in with someone you hate for 2 years and get 1 million € as a reward',
        'not get 1 million €', '10a', '10b'),
    ('never leave the house for a year',
        'sleep only 3 hours every night for a year', '11a', '11b'),
    ('eat only your least favorite food for the rest of your life',
        'never listen to music again', '12a', '12b'),
    ('live somewhere where the sun never shines',
        'its always over 30°', '13a', '13b'),
    ('have a roommate who is nice but never cleans',
        'a roommate who is clean but not nice', '14a', '14b')
;