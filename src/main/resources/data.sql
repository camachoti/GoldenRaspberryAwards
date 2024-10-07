INSERT INTO "movie" ("year", "title", "studio", "producer", "winner")
SELECT
    "YEAR", "TITLE", "STUDIOS", "PRODUCERS",  coalesce(convert("WINNER", boolean), false)
FROM CSVREAD('movielist.csv', NULL,
             'fieldSeparator=;');