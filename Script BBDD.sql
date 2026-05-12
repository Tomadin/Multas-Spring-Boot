-- Script de inserción de datos para prueba-mvc
-- Orden correcto según dependencias de claves foráneas

-- ==================================================
-- TABLAS SIN DEPENDENCIAS (Se insertan primero)
-- ==================================================

-- 1. ORGANIZACION (Sin dependencias)
INSERT INTO `organizacion` (`LOCALIDAD`, `NOMBREORGANIZACION`) VALUES
('Lujan de Cuyo', 'Municipalidad Lujan de Cuyo'),
('Mendoza', 'Policia Federal'),
('Godoy Cruz', 'Policia Vial'),
('Las Heras', 'Municipalidad Las Heras'),
('Guaymallen', 'Policia Provincial'),
('Maipu', 'Municipalidad Maipu'),
('San Rafael', 'Policia Caminera'),
('Tunuyan', 'Municipalidad Tunuyan'),
('San Martin', 'Policia de Transito'),
('Capital', 'Policia Municipal Capital');

-- 2. TIPOSRUTAS (Sin dependencias)
INSERT INTO `tiposrutas` (`DESCTIPORUTA`, `NOMBRETIPODERUTA`) VALUES
('Rutas nacionales principales', 'Nacional'),
('Rutas provinciales', 'Provincial'),
('Calles urbanas', 'Urbana'),
('Autopistas de alta velocidad', 'Autopista'),
('Caminos rurales', 'Rural'),
('Rutas interprovinciales', 'Interprovincial'),
('Avenidas principales', 'Avenida'),
('Calles de montaña', 'Montaña'),
('Rutas turisticas', 'Turistica'),
('Caminos vecinales', 'Vecinal');

-- 3. RUTAS (Depende de TIPOSRUTAS)
INSERT INTO `rutas` (`KMRUTA`, `NOMBRERUTA`, `tipo_ruta_id`) VALUES
('730', 'Ruta Nacional 7', 1),
('850', 'Ruta Provincial 82', 2),
('15', 'Av. San Martin', 7),
('1200', 'Autopista del Sol', 4),
('45', 'Camino a Potrerillos', 5),
('560', 'Ruta Provincial 15', 2),
('28', 'Av. Acceso Este', 7),
('320', 'Ruta Nacional 40', 1),
('95', 'Camino El Challao', 8),
('180', 'Ruta del Vino', 9);

-- 4. MODELOS (Sin dependencias)
INSERT INTO `modelos` (`MODELOAUTO`) VALUES
('Fiesta'),
('Corolla'),
('Gol'),
('Polo'),
('Focus'),
('Cruze'),
('Cronos'),
('Onix'),
('Ranger'),
('Hilux');

-- 5. MARCAS (Depende de MODELOS)
INSERT INTO `marcas` (`MARCAAUTO`, `modelo_id`) VALUES
('Ford', 1),
('Toyota', 2),
('Volkswagen', 3),
('Volkswagen', 4),
('Ford', 5),
('Chevrolet', 6),
('Fiat', 7),
('Chevrolet', 8),
('Ford', 9),
('Toyota', 10);

-- 6. VEHICULO (Depende de MARCAS)
INSERT INTO `vehiculo` (`ANIOPATENTAMIENTO`, `COLOR`, `DOMINIO`, `marca_id`) VALUES
(2020, 'Rojo', 'AA123BB', 1),
(2019, 'Blanco', 'AB456CD', 2),
(2021, 'Negro', 'AC789EF', 3),
(2018, 'Gris', 'AD012GH', 4),
(2022, 'Azul', 'AE345IJ', 5),
(2017, 'Verde', 'AF678KL', 6),
(2023, 'Plata', 'AG901MN', 7),
(2020, 'Blanco', 'AH234OP', 8),
(2019, 'Negro', 'AI567QR', 9),
(2021, 'Rojo', 'AJ890ST', 10);

-- 7. PERSONA (Sin dependencias - Tabla base de herencia)
INSERT INTO `persona` (`DNI`, `tipo`, `APELLIDO`, `GENERO`, `NOMBRE`, `IDLEGAJO`, `IDPLACA`, `DOMICILIO`) VALUES
(41456732, 'AUTORIDAD', 'Diaz', 'mujer', 'Paula', 86523, 8765653, NULL),
(42653476, 'AUTORIDAD', 'Smith', 'hombre', 'Brian', 734663, 876523, NULL),
(44567634, 'AUTORIDAD', 'Suarez', 'hombre', 'Agustin', 876545, 987675, NULL),
(38456789, 'AUTORIDAD', 'Rodriguez', 'mujer', 'Maria', 765432, 654321, NULL),
(40123456, 'AUTORIDAD', 'Gonzalez', 'hombre', 'Carlos', 543210, 432109, NULL),
(42653477, 'CONDUCTOR', 'Perez', 'hombre', 'Juan', NULL, NULL, 'San Martin 123'),
(44567635, 'CONDUCTOR', 'Lopez', 'mujer', 'Ana', NULL, NULL, 'Belgrano 456'),
(39876543, 'CONDUCTOR', 'Martinez', 'hombre', 'Pedro', NULL, NULL, 'Mitre 789'),
(41234567, 'CONDUCTOR', 'Fernandez', 'mujer', 'Laura', NULL, NULL, 'Sarmiento 321'),
(43567890, 'CONDUCTOR', 'Garcia', 'hombre', 'Diego', NULL, NULL, 'Rivadavia 654');

-- 8. LICENCIA (Depende de PERSONA - conductor)
INSERT INTO `licencia` (`fecha_vencimiento`, `NUMEROLICENCIA`, `puntos`, `conductor_dni`) VALUES
('2026-12-31 00:00:00', 3232213, 86, 42653477),
('2025-06-30 00:00:00', 1122334, 92, 44567635),
('2027-03-15 00:00:00', 5566778, 78, 39876543),
('2026-09-20 00:00:00', 9988776, 100, 41234567),
('2025-11-10 00:00:00', 4455667, 65, 43567890),
('2028-01-25 00:00:00', 7788990, 88, 42653477),
('2026-05-18 00:00:00', 2233445, 95, 44567635),
('2027-08-08 00:00:00', 6677889, 72, 39876543),
('2025-12-12 00:00:00', 3344556, 80, 41234567),
('2026-07-30 00:00:00', 8899001, 90, 43567890);

-- 9. ESTADODELACTA (Sin dependencias)
INSERT INTO `estadodelacta` (`descripcion`, `estado`) VALUES
('Acta recien labrada, pendiente de revision', 'PENDIENTE'),
('Acta verificada y aprobada', 'APROBADA'),
('Acta con pago realizado', 'PAGADA'),
('Acta en proceso de apelacion', 'EN_APELACION'),
('Apelacion rechazada', 'APELACION_RECHAZADA'),
('Apelacion aprobada, acta anulada', 'ANULADA'),
('Acta vencida sin pago', 'VENCIDA'),
('En proceso de cobro judicial', 'EN_COBRO_JUDICIAL'),
('Acta con plan de pagos activo', 'PLAN_PAGOS'),
('Acta prescripta por tiempo', 'PRESCRIPTA');

-- 10. ACTAS (Depende de: PERSONA-autoridad, VEHICULO, ORGANIZACION, LICENCIA, RUTA, ESTADODELACTA)
INSERT INTO `actas` (`FECHADELABRADO`, `FECHAVTOPAGOVOLUN`, `HORADELABRADO`, `LUGARDECONSTATACION`, `OBSERVACIONES`, `autoridad_id`, `estado_id`, `licencia_id`, `organizacion_id`, `ruta_id`, `vehiculo_id`) VALUES
('2025-11-14 20:57:11', '2025-12-14 20:57:13', '2025-11-14 20:57:15.679', 'Km 730 Ruta 7', 'Exceso de velocidad detectado', 41456732, 1, 1, 1, 1, 1),
('2025-11-22 21:15:36', '2025-12-22 21:15:37', '2025-11-22 21:15:40.739', 'Av. San Martin altura 1500', 'Estacionamiento indebido', 42653476, 2, 2, 2, 3, 2),
('2025-11-21 14:38:45', '2025-12-21 14:38:48', '2025-11-21 14:38:50.498', 'Rotonda acceso este', 'No respeto prioridad de paso', 41456732, 3, 3, 3, 7, 3),
('2025-11-28 14:46:52', '2025-12-28 14:46:53', '2025-11-28 14:46:56.143', 'Autopista del Sol Km 45', 'Conducir sin cinturon de seguridad', 42653476, 4, 4, 1, 4, 4),
('2025-11-13 14:57:55', '2025-12-13 14:57:58', '2025-11-13 14:57:59.966', 'Camino a Potrerillos', 'Luz de freno quemada', 44567634, 5, 5, 2, 5, 5),
('2025-11-05 10:20:00', '2025-12-05 10:20:00', '2025-11-05 10:20:30.123', 'Ruta Provincial 15 Km 200', 'Adelantamiento indebido', 38456789, 1, 6, 3, 6, 6),
('2025-11-18 16:45:22', '2025-12-18 16:45:22', '2025-11-18 16:45:55.456', 'Av. Acceso Este y Godoy Cruz', 'Semaforo en rojo', 40123456, 2, 7, 4, 7, 7),
('2025-11-25 08:30:10', '2025-12-25 08:30:10', '2025-11-25 08:30:45.789', 'Ruta 40 altura Tunuyan', 'Documentacion vencida', 41456732, 6, 8, 5, 8, 8),
('2025-11-10 19:15:33', '2025-12-10 19:15:33', '2025-11-10 19:16:00.234', 'Camino El Challao', 'Conduccion temeraria', 42653476, 7, 9, 6, 9, 9),
('2025-11-02 12:00:00', '2025-12-02 12:00:00', '2025-11-02 12:00:30.567', 'Ruta del Vino - Maipu', 'Control de alcoholemia positivo', 44567634, 8, 10, 7, 10, 10);

-- 11. TIPODEINFRACCION (Sin dependencias)
INSERT INTO `tipodeinfraccion` (`DESCRIPCIONINFRACCION`, `IMPORTEASIGNADOINFRACCION`, `PORCENTAJEDESCUENTO`, `TIPOGRAVEDAD`) VALUES
('Exceso de velocidad hasta 20 km/h', 15000, 30, 'LEVE'),
('Exceso de velocidad entre 20-40 km/h', 30000, 20, 'MODERADA'),
('Exceso de velocidad mas de 40 km/h', 60000, 10, 'GRAVE'),
('Estacionamiento prohibido', 8000, 40, 'LEVE'),
('No respetar semaforo en rojo', 25000, 15, 'GRAVE'),
('Conducir sin cinturon de seguridad', 12000, 25, 'MODERADA'),
('Conducir usando celular', 18000, 20, 'MODERADA'),
('Documentacion vehicular vencida', 20000, 30, 'MODERADA'),
('No respetar prioridad de paso', 22000, 15, 'GRAVE'),
('Conduccion bajo efectos de alcohol', 100000, 0, 'MUY_GRAVE');

-- 12. INFRACCION (Depende de ACTAS)
INSERT INTO `infraccion` (`DESCRIPCION`, `IMPORTEINFRACCION`, `acta_id`) VALUES
('Circulaba a 140 km/h en zona de 120 km/h', 15000, 1),
('Vehiculo estacionado en zona de carga y descarga', 8000, 2),
('No respeto prioridad en rotonda causando riesgo', 22000, 3),
('Conductor y acompañante sin cinturon', 12000, 4),
('Luz de freno trasera derecha sin funcionar', 5000, 5),
('Adelantamiento en curva con linea continua', 30000, 6),
('Cruzo semaforo 3 segundos despues de rojo', 25000, 7),
('Cedula verde vencida hace 4 meses', 20000, 8),
('Maniobra zigzagueante entre vehiculos', 35000, 9),
('Alcoholemia: 1.2 g/l (limite 0.5 g/l)', 100000, 10);

-- 13. INFRACCION_TIPOINFRACCION (Depende de INFRACCION y TIPODEINFRACCION)
-- Relaciona cada infraccion con su tipo correspondiente
INSERT INTO `infraccion_tipoinfraccion` (`infraccion_id`, `tipo_infraccion_id`) VALUES
(1, 1),  -- Exceso hasta 20 km/h
(2, 4),  -- Estacionamiento prohibido
(3, 9),  -- No respetar prioridad
(4, 6),  -- Sin cinturon
(5, 8),  -- Documentacion vencida
(6, 2),  -- Exceso entre 20-40 km/h
(7, 5),  -- Semaforo en rojo
(8, 8),  -- Documentacion vencida
(9, 2),  -- Exceso entre 20-40 km/h (conduccion temeraria)
(10, 10); -- Conduccion bajo alcohol

-- ==================================================
-- VERIFICACION DE REGISTROS INSERTADOS
-- ==================================================

SELECT 'organizacion' as tabla, COUNT(*) as registros FROM organizacion
UNION ALL
SELECT 'tiposrutas', COUNT(*) FROM tiposrutas
UNION ALL
SELECT 'rutas', COUNT(*) FROM rutas
UNION ALL
SELECT 'modelos', COUNT(*) FROM modelos
UNION ALL
SELECT 'marcas', COUNT(*) FROM marcas
UNION ALL
SELECT 'vehiculo', COUNT(*) FROM vehiculo
UNION ALL
SELECT 'persona', COUNT(*) FROM persona
UNION ALL
SELECT 'licencia', COUNT(*) FROM licencia
UNION ALL
SELECT 'estadodelacta', COUNT(*) FROM estadodelacta
UNION ALL
SELECT 'actas', COUNT(*) FROM actas
UNION ALL
SELECT 'tipodeinfraccion', COUNT(*) FROM tipodeinfraccion
UNION ALL
SELECT 'infraccion', COUNT(*) FROM infraccion
UNION ALL
SELECT 'infraccion_tipoinfraccion', COUNT(*) FROM infraccion_tipoinfraccion;

