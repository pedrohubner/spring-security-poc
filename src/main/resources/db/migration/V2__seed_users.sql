-- Senha em texto puro de ambos: 123 (apenas para testes locais)
insert into users (username, password, first_name, last_name, role)
values ('user', '$2b$10$mP4Dj1lnvrasJfqcpT7EY.NMT9RGoH18KtMZq5CAlgRdSc6Ubwr4S', 'Normal', 'User', 'ROLE_USER'),
       ('admin', '$2b$10$aJDfhArRjx.B1Vtd/Xsr0.3/zwq85Je4QqJ6lr8PRXJ1ZtohDjNXy', 'Admin', 'User', 'ROLE_ADMIN');
