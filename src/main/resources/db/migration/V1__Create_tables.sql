CREATE TABLE usuario (
    usuario_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(50) CHECK (tipo_usuario IN ('cliente', 'chofer')) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

-- Tabla Vehículo
CREATE TABLE vehiculo (
    vehiculo_id SERIAL PRIMARY KEY,
    conductor_id INT REFERENCES usuario(usuario_id),
    tipo_vehiculo VARCHAR(100),
    capacidad_carga DECIMAL(10, 2), -- en toneladas
    placa VARCHAR(50) UNIQUE,
    seguro_activo BOOLEAN DEFAULT FALSE,
    estado_vehiculo VARCHAR(50)
);

-- Tabla Viaje
CREATE TABLE viaje (
    viaje_id SERIAL PRIMARY KEY,
    cliente_id INT REFERENCES usuario(usuario_id),
    conductor_id INT REFERENCES usuario(usuario_id),
    
    -- Coordenadas de origen
    origen_latitud DOUBLE PRECISION NOT NULL,
    origen_longitud DOUBLE PRECISION NOT NULL,
    
    -- Coordenadas de destino
    destino_latitud DOUBLE PRECISION NOT NULL,
    destino_longitud DOUBLE PRECISION NOT NULL,
    
    fecha_hora_carga TIMESTAMP NOT NULL,
    fecha_hora_entrega TIMESTAMP NOT NULL,
    peso DECIMAL(10, 2), -- en toneladas
    volumen DECIMAL(10, 2), -- en metros cúbicos
    
    estado_viaje VARCHAR(50) CHECK (estado_viaje IN ('disponible', 'aceptado', 'en curso', 'finalizado', 'cancelado')),
    costo_total DECIMAL(10, 2),

    -- Descripción del viaje
    descripcion VARCHAR(500),

    -- Observaciones en el punto de origen
    observacion_origen VARCHAR(500),

    -- Observaciones en el punto de destino
    observacion_destino VARCHAR(500)
);


-- Tabla Mercancía
CREATE TABLE mercancia (
    mercancia_id SERIAL PRIMARY KEY,
    viaje_id INT REFERENCES viaje(viaje_id) ON DELETE CASCADE,
    tipo_mercancia VARCHAR(100) NOT NULL,
    volumen DECIMAL(10, 2) NOT NULL, -- en metros cúbicos
    peso DECIMAL(10, 2) NOT NULL, -- en toneladas
    estibas INT NOT NULL, -- Número de estibas utilizadas
    seguro BOOLEAN DEFAULT FALSE NOT NULL, -- Si tiene seguro
    descripcion TEXT -- Descripción adicional de la mercancía
);


-- Tabla Pago
CREATE TABLE pago (
    pago_id SERIAL PRIMARY KEY,
    viaje_id INT REFERENCES viaje(viaje_id) ON DELETE CASCADE,
    forma_pago VARCHAR(50) CHECK (forma_pago IN ('tarjeta', 'efectivo', 'transferencia')) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    impuestos DECIMAL(10, 2) NOT NULL,
    descuento DECIMAL(10, 2) DEFAULT 0,
    total DECIMAL(10, 2) NOT NULL,
    estado_pago VARCHAR(50) CHECK (estado_pago IN ('pendiente', 'completado')) NOT NULL,
    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Tabla Solicitud de Viaje (para que los choferes acepten o rechacen)
CREATE TABLE solicitud_viaje (
    solicitud_id SERIAL PRIMARY KEY,
    viaje_id INT REFERENCES viaje(viaje_id),
    chofer_id INT REFERENCES usuario(usuario_id),
    estado_solicitud VARCHAR(50) CHECK (estado_solicitud IN ('pendiente', 'aceptada', 'rechazada')),
    fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Mensajería (comunicación entre cliente y chofer)
CREATE TABLE mensajeria (
    mensaje_id SERIAL PRIMARY KEY,
    viaje_id INT REFERENCES viaje(viaje_id),
    remitente_id INT REFERENCES usuario(usuario_id),
    destinatario_id INT REFERENCES usuario(usuario_id),
    contenido TEXT NOT NULL,
    fecha_hora_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla Calificación (calificación del cliente y chofer)
CREATE TABLE calificacion (
    calificacion_id SERIAL PRIMARY KEY,
    viaje_id INT REFERENCES viaje(viaje_id),
    evaluador_id INT REFERENCES usuario(usuario_id),
    evaluado_id INT REFERENCES usuario(usuario_id),
    calificacion INT CHECK (calificacion >= 1 AND calificacion <= 5),
    comentario TEXT
);

-- Tabla cooperativa
CREATE TABLE cooperativa (
    cooperativa_id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ruc VARCHAR(13) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    correo VARCHAR(100) UNIQUE NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creado_por VARCHAR(100),
    actualizado_por VARCHAR(100),
    estado VARCHAR(50) DEFAULT 'activo'
);

-- Tabla cooperativa_chofer
CREATE TABLE cooperativa_chofer (
    cooperativa_id INT NOT NULL,
    chofer_id INT NOT NULL,
    fecha_asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cooperativa_id) REFERENCES cooperativa(cooperativa_id),
    FOREIGN KEY (chofer_id) REFERENCES usuario(usuario_id),
    PRIMARY KEY (cooperativa_id, chofer_id)
);

-- Tabla Vehículo
CREATE TABLE parametro (
    parametro_id SERIAL PRIMARY KEY,
    nombre_parametro VARCHAR(100),
    valor1 INTEGER,  -- Cambiado a tipo de dato entero
    valor2 VARCHAR(500),
    valor3 VARCHAR(500),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creado_por VARCHAR(100),
    actualizado_por VARCHAR(100),
    estado VARCHAR(50) DEFAULT 'activo'
);

CREATE TABLE parametro (
    parametro_id SERIAL PRIMARY KEY,
    nombre_parametro VARCHAR(100),
    valor1 INTEGER,  -- Cambiado a tipo de dato entero
    valor2 VARCHAR(500),
    valor3 VARCHAR(500),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creado_por VARCHAR(100),
    actualizado_por VARCHAR(100),
    estado VARCHAR(50) DEFAULT 'activo'
);

CREATE TABLE tipo_usuario (
    tipo_usuario_id SERIAL PRIMARY KEY,
    nombre_tipo_usuario VARCHAR(100) NOT NULL,
    descripcion TEXT,
    estado VARCHAR(50) DEFAULT 'activo',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE usuario
ADD COLUMN tipo_usuario_id INT,
ADD CONSTRAINT fk_tipo_usuario
    FOREIGN KEY (tipo_usuario_id)
    REFERENCES tipo_usuario(tipo_usuario_id)
    ON DELETE SET NULL;



CREATE TABLE tipo_vehiculo (
    tipo_vehiculo_id SERIAL PRIMARY KEY,
    nombre_tipo_vehiculo VARCHAR(100) NOT NULL UNIQUE, -- Ejemplo: 'CAMION', 'CAMIONETA', etc.
    descripcion TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'activo'
);


CREATE TABLE tipo_furgon (
    tipo_furgon_id SERIAL PRIMARY KEY,
    nombre_tipo_furgon VARCHAR(100) NOT NULL UNIQUE, -- Ejemplo: 'REFRIGERADO', 'SECADO', etc.
    descripcion TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'activo'
);


ALTER TABLE vehiculo
ADD COLUMN tipo_vehiculo_id INT,
ADD COLUMN tipo_furgon_id INT,
ADD CONSTRAINT fk_tipo_vehiculo FOREIGN KEY (tipo_vehiculo_id) REFERENCES tipo_vehiculo(tipo_vehiculo_id),
ADD CONSTRAINT fk_tipo_furgon FOREIGN KEY (tipo_furgon_id) REFERENCES tipo_furgon(tipo_furgon_id);




ALTER TABLE viaje
    DROP COLUMN origen,
    DROP COLUMN destino;
    
    
    ALTER TABLE viaje ALTER COLUMN conductor_id DROP NOT NULL;

    CREATE TABLE rastreo (
    rastreo_id SERIAL PRIMARY KEY,
    viaje_id INT REFERENCES viaje(viaje_id) ON DELETE CASCADE,
    latitud DECIMAL(10, 6) NOT NULL,
    longitud DECIMAL(10, 6) NOT NULL,
    fecha_hora_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
    
