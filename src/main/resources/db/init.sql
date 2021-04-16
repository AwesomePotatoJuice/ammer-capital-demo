create table users
(
    id    bigserial primary key,
    login varchar unique not null
);
create table user_accounts
(
    id      bigserial primary key,
    name    varchar,
    balance numeric not null,
    user_id bigint,
    constraint fk_users foreign key (user_id) references users (id) on delete cascade on update cascade
);
create table user_accounts_operations
(
    id               bigserial primary key,
    change           numeric not null,
    account_id       bigint,
    operation_type   varchar not null,
    create_date_time timestamp,
    constraint fk_users foreign key (account_id) references user_accounts (id) on delete cascade on update cascade
);
-- user values
insert into users (login)
values ('test');
insert into users (login)
values ('test1');

-- user account values
-- user 'test'
insert into user_accounts (name, balance, user_id)
values ('test account 1', 100, 1);
insert into user_accounts (name, balance, user_id)
values ('test account 2', 0, 1);
insert into user_accounts (name, balance, user_id)
values ('test account 3', 1000, 1);
-- user 'test1'
insert into user_accounts (name, balance, user_id)
values ('test account 1', 100, 2);
CREATE SEQUENCE hibernate_sequence START 1;