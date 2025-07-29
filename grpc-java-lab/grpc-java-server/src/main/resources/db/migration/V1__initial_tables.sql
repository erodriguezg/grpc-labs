--- CREATE EXTENSION IF NOT EXISTS "pgcrypto";

create table regiones (
	id_region int4 primary key,
	nombre varchar(255)
);

create table provincias (
	id_provincia int4 primary key,
	region_id int4 not null references regiones(id_region),
	nombre varchar(255)
);

create table comunas (
	id_comuna int4 primary key,
	provincia_id int4 not null references provincias(id_provincia),
	nombre varchar(255)
);

create table comunidad_tipos (
	id_comunidad_tipo int4 primary key,
	nombre varchar(255)
);

insert into comunidad_tipos (id_comunidad_tipo, nombre) values
	(1, 'Condominio'),
	(2, 'Edificio');

create table comunidades (
	id_comunidad uuid primary key default gen_random_uuid(),
	comunidad_tipo_id int4 not null references comunidad_tipos(id_comunidad_tipo),
	comuna_id int4 references comunas(id_comuna),
	nombre varchar(255) not null,
	direccion varchar(500),
	fecha_creacion timestamp not null,
	fecha_modificacion timestamp
);

create table recinto_comun_tipos (
	id_recinto_comun_tipo int4 primary key,
	nombre varchar(255)
);

insert into recinto_comun_tipos (id_recinto_comun_tipo, nombre) values
	(1, 'Piscina'),
	(2, 'Quincho'),
	(3, 'Gimnasio'),
	(4, 'Sala Multiuso');

create table recintos_comunes (
	id_recinto_comun uuid primary key default gen_random_uuid(),
	comunidad_id uuid not null references comunidades(id_comunidad),
	recinto_comun_tipo int4 not null references recinto_comun_tipos(id_recinto_comun_tipo),
	descripcion varchar(500)
);

create table personas (
	id_persona uuid primary key default gen_random_uuid(),
	rut int8 unique,
	nombres varchar(255) not null,
	apellidos varchar(255) not null,
	telefono_uno varchar(255),
	telefono_dos varchar(255),
	fecha_creacion timestamp not null,
	fecha_modificacion timestamp
);

create table sistema_perfiles (
	id_sistema_perfil int4 primary key,
	nombre varchar(255)
);

insert into sistema_perfiles (id_sistema_perfil, nombre) values
	(1, 'Super Administrador'),
	(2, 'Usuario');

create table usuarios (
	id_usuario uuid primary key default gen_random_uuid(),
	sistema_perfil_id int4 not null references sistema_perfiles(id_sistema_perfil),
	persona_id uuid references personas(id_persona),
	email varchar(255) not null unique,
	password varchar(500),
	fecha_creacion timestamp not null,
	fecha_modificacion timestamp
);

create table comunidad_administradores (
	id_comunidad_administrador bigserial primary key,
	comunidad_id uuid not null references comunidades(id_comunidad),
	usuario_id uuid not null references usuarios(id_usuario)
);

create table estados_reservas (
	id_estado_reserva int4 primary key,
	nombre varchar(255)
);

insert into estados_reservas (id_estado_reserva, nombre) values
	(1, 'Solicitada'),
	(2, 'Aceptada'),
	(3, 'Rechazada'),
	(4, 'Cancelada');

create table reservas (
	id_reserva uuid primary key default gen_random_uuid(),
	recinto_comun_id uuid not null references recintos_comunes(id_recinto_comun),
	persona_id uuid not null references personas(id_persona),
	estado_reserva_id int4 not null references estados_reservas(id_estado_reserva),
	fecha_reserva date not null,
	hora_inicio timestamp not null,
	hora_fin timestamp not null,
	fecha_creacion timestamp not null,
	razon_rechazo varchar(255)
);

create table inmueble_tipos (
	id_inmueble_tipo int4 primary key,
	nombre varchar(255)
);

insert into inmueble_tipos (id_inmueble_tipo, nombre) values
	(1, 'Departamento'),
	(2, 'Casa'),
	(3, 'Oficina'),
	(4, 'Local Comercial');

create table inmuebles (
	id_inmueble uuid primary key default gen_random_uuid(),
	comunidad_id uuid not null references comunidades(id_comunidad),
	inmueble_tipo_id int4 not null references inmueble_tipos(id_inmueble_tipo),
	numero int4 not null,
	porcentaje_copropiedad int4,
	fecha_creacion timestamp not null,
	fecha_modificacion timestamp,
	constraint uq_inmueble_numero_comunidad unique (comunidad_id, numero)
);

create table inmueble_anexo_tipos (
	id_inmueble_anexo_tipo int4 primary key,
	nombre varchar(255)
);

insert into inmueble_anexo_tipos (id_inmueble_anexo_tipo, nombre) values
	(1, 'Estacionamiento'),
	(2, 'Bodega');

create table inmueble_anexos (
	id_inmueble_anexo uuid primary key default gen_random_uuid(),
	inmueble_id uuid not null references inmuebles(id_inmueble),
	inmueble_anexo_tipo_id int4 not null references inmueble_anexo_tipos(id_inmueble_anexo_tipo),
	nombre varchar(255) not null,
	descripcion varchar(500)
);

create table copropietarios (
	id_copropietario uuid primary key default gen_random_uuid(),
	inmueble_id uuid not null references inmuebles(id_inmueble),
	persona_id uuid not null references personas(id_persona),
	fecha_inicio date not null,
	fecha_termino date
);

create table arrendatarios (
	id_arrendatario uuid primary key default gen_random_uuid(),
	inmueble_id uuid not null references inmuebles(id_inmueble),
	persona_id uuid not null references personas(id_persona),
	fecha_inicio date not null,
	fecha_termino date
);
