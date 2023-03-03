create type employee_status as enum
  ('ACTIVE', 'INACTIVE');

create table if not exists employee_contact (
  id               uuid             not null primary key,
  name             text             not null,
  email            text             not null,
  job_title        text             not null,
  phone_number     text             not null,
  image_url        text             not null
);

create index if not exists idx_employee_contact_name on employee_contact(name);

create table if not exists employee (
  id                     bigint             not null primary key,
  employee_code          text               not null,
  status                 employee_status    not null,
  started                timestamptz        not null,
  employee_contact_id    uuid               not null,
  constraint fk_employee__employee_contact_id
      foreign key(employee_contact_id)
      references employee_contact(id)
      on delete cascade
      on update cascade
);

create index if not exists idx_employee_employee_code on employee(employee_code);
create index if not exists idx_employee_started on employee(started);

create sequence if not exists employee_seq;
