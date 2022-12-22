alter table medicos
    add column ativo tinyint;
update medicos set medicos.ativo = 1;