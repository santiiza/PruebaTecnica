-- ============================================
-- BASE DE DATOS: clientes_db
-- MICROservicio: clientes-service
-- CONTEXTO: CORE (Persona - Cliente)
-- ============================================

CREATE SCHEMA IF NOT EXISTS core AUTHORIZATION postgres;

-- ==============================
-- Tabla: Persona
-- ==============================
CREATE TABLE core.co_persona (
  pe_id                 BIGSERIAL    PRIMARY KEY,
  pe_nombre             VARCHAR(80)  NOT NULL,
  pe_genero             VARCHAR(10)  NOT NULL,
  pe_edad               INTEGER,
  pe_identificacion     VARCHAR(15)  NOT NULL UNIQUE,
  pe_direccion          VARCHAR(256),
  pe_telefono           VARCHAR(20)
);
CREATE INDEX idx_co_persona_identificacion
  ON core.co_persona (pe_identificacion);

-- ==============================
-- Tabla: Cliente
-- ==============================
CREATE TABLE core.co_cliente (
  pe_id           BIGINT       PRIMARY KEY,
  cl_id           VARCHAR(30)  NOT NULL UNIQUE,
  cl_contrasenia  VARCHAR(256) NOT NULL,
  cl_estado       BOOLEAN      NOT NULL,
  CONSTRAINT fk_cliente_persona
  FOREIGN KEY (pe_id) REFERENCES core.co_persona (pe_id)
);
CREATE INDEX idx_co_cliente_cl_id
  ON core.co_cliente (cl_id);

-- ============================================
-- BASE DE DATOS: cuentas_db
-- MICROservicio: cuentas-service
-- CONTEXTO: PASIVO (Cuenta - Movimiento)
-- ============================================

CREATE SCHEMA IF NOT EXISTS pasivo AUTHORIZATION postgres;

-- ==============================
-- Tabla: Cuenta
-- ==============================
CREATE TABLE pasivo.pa_cuenta (
  cu_numero         VARCHAR(20)   PRIMARY KEY,
  cl_id             VARCHAR(30)   NOT NULL,
  cu_tipo_cuenta    VARCHAR(20)   NOT NULL,
  cu_saldo_inicial  DECIMAL(15,2) NOT NULL DEFAULT 0.00,
  cu_estado         BOOLEAN       NOT NULL
);
CREATE INDEX idx_pa_cuenta_cliente
  ON pasivo.pa_cuenta (cl_id);

-- ==============================
-- Tabla: Movimiento
-- ==============================
CREATE TABLE pasivo.pa_movimiento (
  mo_id               BIGSERIAL     PRIMARY KEY,
  cu_numero           VARCHAR(20)   NOT NULL,
  mo_fecha            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  mo_tipo_movimiento  VARCHAR(20)   NOT NULL,
  mo_monto            DECIMAL(15,2) NOT NULL,
  mo_saldo            DECIMAL(15,2) NOT NULL,
  CONSTRAINT fk_movimiento_cuenta
  FOREIGN KEY (cu_numero) REFERENCES pasivo.pa_cuenta (cu_numero)
);

CREATE INDEX idx_pa_movimiento_fecha
  ON pasivo.pa_movimiento (mo_fecha);

CREATE INDEX idx_pa_movimiento_numero
  ON pasivo.pa_movimiento (cu_numero);

-- ==============================
-- Vista: Cliente
-- ==============================
CREATE TABLE pasivo.cliente_view (
  id                  VARCHAR(30)   PRIMARY KEY,
  nombre              VARCHAR(80)   NOT NULL,
  estado              BOOLEAN       NOT NULL
);

CREATE INDEX idx_cliente_view
  ON pasivo.cliente_view (nombre);

