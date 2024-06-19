-- Inserir alguns departamentos
INSERT INTO DEPARTAMENTO (nome, local) VALUES ('TI', 'Prédio A');
INSERT INTO DEPARTAMENTO (nome, local) VALUES ('RH', 'Prédio B');
INSERT INTO DEPARTAMENTO (nome, local) VALUES ('Financeiro', 'Prédio C');

-- Inserir alguns funcionários
INSERT INTO FUNCIONARIO (nome, endereco, telefone, email, data_nascimento, departamento_id) VALUES ('João Silva', 'Rua A, 123', '111111111', 'joao.silva@example.com', '1980-01-01', 1);
INSERT INTO FUNCIONARIO (nome, endereco, telefone, email, data_nascimento, departamento_id) VALUES ('Maria Oliveira', 'Rua B, 456', '222222222', 'maria.oliveira@example.com', '1990-02-02', 2);
INSERT INTO FUNCIONARIO (nome, endereco, telefone, email, data_nascimento, departamento_id) VALUES ('Pedro Santos', 'Rua C, 789', '333333333', 'pedro.santos@example.com', '1985-03-03', 3);
INSERT INTO FUNCIONARIO (nome, endereco, telefone, email, data_nascimento, departamento_id) VALUES ('Ana Costa', 'Rua D, 101', '444444444', 'ana.costa@example.com', '1995-04-04', 1);