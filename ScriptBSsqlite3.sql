-- -----------------------------------------------------
-- Table `Musculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Musculo (
  idMusculo INTEGER NOT NULL PRIMARY KEY,
  Nombre TEXT NOT NULL,
  Grupo TEXT NOT NULL CHECK (Grupo IN ('push', 'pull', 'leg'))
);

-- -----------------------------------------------------
-- Table `Ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Ejercicio (
  idEjercicio INTEGER NOT NULL,
  mejorMarca REAL,
  Musculo_idMusculo INTEGER NOT NULL,
  PRIMARY KEY (idEjercicio, Musculo_idMusculo),
  FOREIGN KEY (Musculo_idMusculo) REFERENCES Musculo (idMusculo)
);

-- -----------------------------------------------------
-- Table `Serie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Serie (
  idSerie INTEGER NOT NULL,
  peso REAL,
  repeticiones INTEGER,
  completado INTEGER CHECK (completado IN (0, 1)),
  Ejercicio_idEjercicio INTEGER NOT NULL,
  Ejercicio_Musculo_idMusculo INTEGER NOT NULL,
  PRIMARY KEY (idSerie, Ejercicio_idEjercicio, Ejercicio_Musculo_idMusculo),
  FOREIGN KEY (Ejercicio_idEjercicio, Ejercicio_Musculo_idMusculo)
    REFERENCES Ejercicio (idEjercicio, Musculo_idMusculo)
);

-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Usuario (
  idUsuario INTEGER NOT NULL PRIMARY KEY,
  email TEXT NOT NULL UNIQUE,
  contraseña TEXT NOT NULL,
  peso REAL,
  idEntrenamiento INTEGER,
  idRutina INTEGER
);

-- -----------------------------------------------------
-- Table `Rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Rutina (
  idRutina INTEGER NOT NULL,
  Nombre TEXT NOT NULL,
  idUsuario INTEGER NOT NULL,
  PRIMARY KEY (idRutina, idUsuario),
  FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario)
);

-- -----------------------------------------------------
-- Table `RutinaEjercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS RutinaEjercicio (
  idRutina INTEGER NOT NULL,
  dEjercicio INTEGER NOT NULL,
  PRIMARY KEY (idRutina, dEjercicio),
  FOREIGN KEY (idRutina) REFERENCES Rutina (idRutina),
  FOREIGN KEY (dEjercicio) REFERENCES Ejercicio (idEjercicio)
);

-- -----------------------------------------------------
-- Table `Entrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Entrenamiento (
  idEntrenamiento INTEGER NOT NULL,
  fechaRealizacion TEXT, -- ISO8601 format (YYYY-MM-DD)
  pesoTotal REAL,
  idRutina INTEGER NOT NULL,
  idUsuario INTEGER NOT NULL,
  PRIMARY KEY (idEntrenamiento, idRutina, idUsuario),
  FOREIGN KEY (idRutina) REFERENCES Rutina (idRutina),
  FOREIGN KEY (idUsuario) REFERENCES Usuario (idUsuario)
);
