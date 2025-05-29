-- Script SQL para criação das tabelas do sistema de gestão de casa de poker

-- Tabela de jogadores
CREATE TABLE player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    chips DOUBLE NOT NULL,
    poker_chips INTEGER NOT NULL
);

-- Tabela de torneios
CREATE TABLE tournament (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    entry_fee DOUBLE NOT NULL,
    prize_pool DOUBLE NOT NULL,
    reentry_allowed BOOLEAN NOT NULL
);

-- Tabela de dealers
CREATE TABLE dealer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    salary DOUBLE NOT NULL
);

-- Tabela de relacionamento entre torneios e jogadores
CREATE TABLE tournament_player (
    tournament_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    PRIMARY KEY (tournament_id, player_id),
    FOREIGN KEY (tournament_id) REFERENCES tournament(id),
    FOREIGN KEY (player_id) REFERENCES player(id)
);

-- Inserção de dados de exemplo para testes

-- Jogadores
INSERT INTO player (name, email, chips, poker_chips) VALUES
('João Silva', 'joao.silva@email.com', 1000.0, 500),
('Maria Oliveira', 'maria.oliveira@email.com', 1500.0, 750),
('Pedro Santos', 'pedro.santos@email.com', 2000.0, 1000);

-- Torneios
INSERT INTO tournament (name, entry_fee, prize_pool, reentry_allowed) VALUES
('Torneio Semanal', 100.0, 1000.0, true),
('Torneio Mensal', 250.0, 5000.0, false),
('Torneio Especial', 500.0, 10000.0, true);

-- Dealers
INSERT INTO dealer (name, salary) VALUES
('Carlos Mendes', 2500.0),
('Ana Pereira', 2700.0);

-- Relacionamentos entre torneios e jogadores
INSERT INTO tournament_player (tournament_id, player_id) VALUES
(1, 1), -- João Silva no Torneio Semanal
(1, 2), -- Maria Oliveira no Torneio Semanal
(2, 1), -- João Silva no Torneio Mensal
(2, 3), -- Pedro Santos no Torneio Mensal
(3, 2), -- Maria Oliveira no Torneio Especial
(3, 3); -- Pedro Santos no Torneio Especial
