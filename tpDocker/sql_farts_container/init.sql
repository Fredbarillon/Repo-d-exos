CREATE DATABASE IF NOT EXISTS fartDB;
USE fartDB;

CREATE TABLE IF NOT EXISTS farts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    smell VARCHAR(100) NOT NULL,
    dissipation INT NOT NULL, -- en secondes
    sound_volume DECIMAL(4,2) NOT NULL, -- en dB
    origin VARCHAR(100) NOT NULL
    );


INSERT INTO farts (smell, dissipation, sound_volume, origin) VALUES
    ('œuf pourri', 15, 87.50, 'canapé'),
    ('choux fermenté', 25, 92.10, 'les toilettes à coté'),
    ('subtil mais piquant', 10, 65.00, 'lit'),
    ('cul de vache', 8, 70.30, 'la belle mère'),
    ('cadavre', 30, 98.90, 'salle de réunion');