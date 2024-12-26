CREATE TABLE IF NOT EXISTS Ejercicio (
    idEjercicio INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT,
    imagen TEXT,
    mejorMarca REAL
);

CREATE TABLE Entrenamiento (
    idEntrenamiento INTEGER PRIMARY KEY AUTOINCREMENT,
    fechaRealizacion TEXT NOT NULL,
    pesoTotal REAL NOT NULL,
    idRutina INTEGER NOT NULL,
    FOREIGN KEY (idRutina) REFERENCES Rutina(idRutina)
);

CREATE TABLE Musculo (
    idMusculo INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    grupoMuscular TEXT NOT NULL CHECK (grupoMuscular IN ('PUSH', 'PULL', 'LEG'))
);

CREATE TABLE MusculoEjercicio (
    idMusculo INTEGER NOT NULL,
    idEjercicio INTEGER NOT NULL,
    PRIMARY KEY (idMusculo, idEjercicio),
    FOREIGN KEY (idMusculo) REFERENCES Musculo(idMusculo) ON DELETE CASCADE,
    FOREIGN KEY (idEjercicio) REFERENCES Ejercicio(idEjercicio) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Rutina (
    idRutina INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    descripcion TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS RutinaEjercicio (
    idRutina INTEGER NOT NULL,
    idEjercicio INTEGER NOT NULL,
    PRIMARY KEY (idRutina, idEjercicio),
    FOREIGN KEY (idRutina) REFERENCES Rutina(idRutina) ON DELETE CASCADE,
    FOREIGN KEY (idEjercicio) REFERENCES Ejercicio(idEjercicio) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Serie (
    idSerie INTEGER PRIMARY KEY AUTOINCREMENT,
    peso REAL,
    repeticiones INTEGER,
    completado INTEGER NOT NULL,
    idEjercicio INTEGER NOT NULL,
    FOREIGN KEY (idEjercicio) REFERENCES Ejercicio(idEjercicio)
);


