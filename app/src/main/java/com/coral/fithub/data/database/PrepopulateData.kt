package com.coral.fithub.data.database

import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.model.GrupoMuscular
import com.coral.fithub.data.model.Musculo
import com.coral.fithub.data.model.MusculoEjercicio

object PrepopulateData {
    val ejercicios = listOf(
        Ejercicio(1, null, "Press de banca con barra"),
        Ejercicio(2, null, "Press de banca inclinado con barra"),
        Ejercicio(3, null, "Press de banca declinado con barra"),
        Ejercicio(4, null, "Press de banca con mancuernas"),
        Ejercicio(5, null, "Press inclinado con mancuernas"),
        Ejercicio(6, null, "Fondos en paralelas"),
        Ejercicio(7, null, "Aperturas con mancuernas"),
        Ejercicio(8, null, "Aperturas en máquina"),
        Ejercicio(9, null, "Pullover con mancuerna"),
        Ejercicio(10, null, "Dominadas (agarre pronado)"),
        Ejercicio(11, null, "Dominadas (agarre supino)"),
        Ejercicio(12, null, "Dominadas (agarre neutro)"),
        Ejercicio(13, null, "Remo con barra"),
        Ejercicio(14, null, "Remo con mancuerna"),
        Ejercicio(15, null, "Remo en máquina"),
        Ejercicio(16, null, "Peso muerto convencional"),
        Ejercicio(17, null, "Peso muerto sumo"),
        Ejercicio(18, null, "Jalón al pecho"),
        Ejercicio(19, null, "Jalón tras nuca"),
        Ejercicio(20, null, "Pull-over en polea"),
        Ejercicio(21, null, "Press militar con barra"),
        Ejercicio(22, null, "Press militar con mancuernas"),
        Ejercicio(23, null, "Elevaciones laterales"),
        Ejercicio(24, null, "Elevaciones frontales"),
        Ejercicio(25, null, "Pájaros (elevaciones posteriores)"),
        Ejercicio(26, null, "Encogimientos de hombros"),
        Ejercicio(27, null, "Face pulls"),
        Ejercicio(28, null, "Press Arnold"),
        Ejercicio(29, null, "Curl con barra"),
        Ejercicio(30, null, "Curl con mancuernas"),
        Ejercicio(31, null, "Curl concentrado"),
        Ejercicio(32, null, "Curl martillo"),
        Ejercicio(33, null, "Curl en predicador"),
        Ejercicio(34, null, "Curl en polea baja"),
        Ejercicio(35, null, "Extensiones de tríceps"),
        Ejercicio(36, null, "Press francés"),
        Ejercicio(37, null, "Press cerrado con barra"),
        Ejercicio(38, null, "Patada de tríceps"),
        Ejercicio(39, null, "Fondos en paralelas para tríceps"),
        Ejercicio(40, null, "Pushdown de triceps en polea"),
        Ejercicio(41, null, "Curl de muñeca"),
        Ejercicio(42, null, "Extensiones de muñeca"),
        Ejercicio(43, null, "Flexiones"),
        Ejercicio(44, null, "Sentadilla con barra (convencional)"),
        Ejercicio(45, null, "Sentadilla con barra (frontal)"),
        Ejercicio(46, null, "Sentadilla búlgara"),
        Ejercicio(47, null, "Peso muerto rumano"),
        Ejercicio(48, null, "Prensa de piernas"),
        Ejercicio(49, null, "Zancadas"),
        Ejercicio(50, null, "Extensiones de piernas en máquina"),
        Ejercicio(51, null, "Curl femoral sentado"),
        Ejercicio(52, null, "Hip thrust"),
        Ejercicio(53, null, "Elevación de talones de pie"),
        Ejercicio(54, null, "Crunch abdominal"),
        Ejercicio(55, null, "Crunch en máquina"),
        Ejercicio(56, null, "Plancha abdominal"),
        Ejercicio(57, null, "Elevación de piernas colgado"),
        Ejercicio(58, null, "Rueda abdominal"),
        Ejercicio(59, null, "Russian twists"),
        Ejercicio(60, null, "Mountain climbers"),
        Ejercicio(61, null, "Cinta de correr"),
        Ejercicio(62, null, "Elíptica"),
        Ejercicio(63, null, "Bicicleta estática"),
        Ejercicio(64, null, "Remo ergométrico"),
        Ejercicio(65, null, "Assault bike"),
        Ejercicio(66, null, "Kettlebell swings"),
        Ejercicio(67, null, "Lanzamiento de balón medicinal"),
        Ejercicio(68, null, "Burpees"),
        Ejercicio(69, null, "Box jumps"),
        Ejercicio(70, null, "Thrusters"),
        Ejercicio(71, null, "Dos tiempos"),
        Ejercicio(72, null, "Arrancada"),
        Ejercicio(73, null, "Muscle-ups"),
        Ejercicio(74, null, "Curl araña"),
        Ejercicio(75, null, "Cables cruzados"),
        Ejercicio(76, null, "Mariposa"),
        Ejercicio(77, null, "Flexiones diamante"),
        Ejercicio(78, null, "Dominada negativa"),
        Ejercicio(79, null, "Remo inclinado"),
        Ejercicio(80, null, "Sentadilla hack"),
        Ejercicio(81, null, "Curl femoral acostado"),
        Ejercicio(82, null, "Elevación de gemelos sentado"),
        Ejercicio(83, null, "Pull through en polea baja"),
        Ejercicio(84, null, "Patada de glúteo"),
        Ejercicio(85, null, "Caminata lateral con banda"),
        Ejercicio(86, null, "Hiperextensiones"),
        Ejercicio(87, null, "Superman"),
        Ejercicio(88, null, "Remo vertical"),
        Ejercicio(89, null, "Abducción de caderas"),
        Ejercicio(90, null, "Aducción de caderas"),
        Ejercicio(91, null, "Cuerda de batalla"),
        Ejercicio(92, null, "Máquina escaladora"),
        Ejercicio(93, null, "Jumping jacks")
    )

    val musculos = listOf(
        Musculo(1, "Pectorales", GrupoMuscular.PUSH),
        Musculo(2, "Deltoides", GrupoMuscular.PUSH),
        Musculo(3, "Tríceps", GrupoMuscular.PUSH),

        Musculo(4, "Dorsales y trapecios", GrupoMuscular.PULL),
        Musculo(5, "Espalda superior", GrupoMuscular.PULL),
        Musculo(6, "Bíceps y antebrazo", GrupoMuscular.PULL),

        Musculo(7, "Cuádriceps", GrupoMuscular.LEG),
        Musculo(8, "Isquiotibiales", GrupoMuscular.LEG),
        Musculo(9, "Gemelos", GrupoMuscular.LEG),
        Musculo(10, "Glúteos", GrupoMuscular.LEG),
        Musculo(11, "Aductores y Abductores", GrupoMuscular.LEG),

        Musculo(12, "Abdomen", GrupoMuscular.CORE),
        Musculo(13, "Espalda baja", GrupoMuscular.CORE),

        Musculo(14, "Trapecio", GrupoMuscular.PULL),
        Musculo(15, "Cardio", GrupoMuscular.CARDIO),
        Musculo(16, "Full Body", GrupoMuscular.FULL_BODY)
    )

    val musculoEjercicio = listOf(
        // Pectorales
        MusculoEjercicio(1, 1), // Press de banca con barra
        MusculoEjercicio(1, 2), // Press de banca inclinado con barra
        MusculoEjercicio(1, 3), // Press de banca declinado con barra
        MusculoEjercicio(1, 4), // Press de banca con mancuernas
        MusculoEjercicio(1, 5), // Press inclinado con mancuernas
        MusculoEjercicio(1, 6), // Fondos en paralelas
        MusculoEjercicio(1, 7), // Aperturas con mancuernas
        MusculoEjercicio(1, 8), // Aperturas en máquina
        MusculoEjercicio(1, 9), // Pullover con mancuerna
        MusculoEjercicio(1, 43), // Flexiones
        MusculoEjercicio(1, 75), // Cables cruzados
        MusculoEjercicio(1, 76), // Mariposa
        MusculoEjercicio(1, 77), // Flexiones diamante

        // Deltoides
        MusculoEjercicio(2, 1), // Press de banca con barra
        MusculoEjercicio(2, 2), // Press de banca inclinado con barra
        MusculoEjercicio(2, 4), // Press de banca con mancuernas
        MusculoEjercicio(2, 5), // Press inclinado con mancuernas
        MusculoEjercicio(2, 21), // Press militar con barra
        MusculoEjercicio(2, 22), // Press militar con mancuernas
        MusculoEjercicio(2, 23), // Elevaciones laterales
        MusculoEjercicio(2, 24), // Elevaciones frontales
        MusculoEjercicio(2, 25), // Pájaros (elevaciones posteriores)
        MusculoEjercicio(2, 27), // Face pulls
        MusculoEjercicio(2, 28), // Press Arnold
        MusculoEjercicio(2, 43), // Flexiones


        // Tríceps
        MusculoEjercicio(3, 1), // Press de banca con barra
        MusculoEjercicio(3, 2), // Press de banca inclinado con barra
        MusculoEjercicio(3, 3), // Press de banca declinado con barra
        MusculoEjercicio(3, 4), // Press de banca con mancuernas
        MusculoEjercicio(3, 5), // Press inclinado con mancuernas
        MusculoEjercicio(3, 6), // Fondos en paralelas
        MusculoEjercicio(3, 22), // Press militar con mancuernas
        MusculoEjercicio(3, 35), // Extensiones de tríceps
        MusculoEjercicio(3, 36), // Press francés
        MusculoEjercicio(3, 37), // Press cerrado con barra
        MusculoEjercicio(3, 38), // Patada de tríceps
        MusculoEjercicio(3, 39), // Fondos en paralelas para tríceps
        MusculoEjercicio(3, 40), // Pushdown
        MusculoEjercicio(3, 43), // Flexiones
        MusculoEjercicio(3, 77), // Flexiones diamante


        // Dorsales
        MusculoEjercicio(4, 10), // Dominadas (agarre pronado)
        MusculoEjercicio(4, 11), // Dominadas (agarre supino)
        MusculoEjercicio(4, 12), // Dominadas (agarre neutro)
        MusculoEjercicio(4, 13), // Remo con barra
        MusculoEjercicio(4, 14), // Remo con mancuerna
        MusculoEjercicio(4, 15), // Remo en máquina
        MusculoEjercicio(4, 18), // Jalón al pecho
        MusculoEjercicio(4, 19), // Jalón tras nuca
        MusculoEjercicio(4, 20), // Pull-over en polea
        MusculoEjercicio(4, 78), // Dominada negativa

        // Espalda superior
        MusculoEjercicio(5, 10), // Dominadas (agarre pronado)
        MusculoEjercicio(5, 11), // Dominadas (agarre supino)
        MusculoEjercicio(5, 13), // Remo con barra
        MusculoEjercicio(5, 14), // Remo con mancuerna
        MusculoEjercicio(5, 15), // Remo en máquina
        MusculoEjercicio(5, 18), // Jalón al pecho
        MusculoEjercicio(5, 19), // Jalón tras nuca
        MusculoEjercicio(5, 25), // Pájaros (elevaciones posteriores)
        MusculoEjercicio(5, 79), // Remo inclinado con barra

        // Bíceps y antebrazo
        MusculoEjercicio(6, 11), // Dominadas (agarre supino)
        MusculoEjercicio(6, 29), // Curl con barra
        MusculoEjercicio(6, 30), // Curl con mancuernas
        MusculoEjercicio(6, 31), // Curl concentrado
        MusculoEjercicio(6, 32), // Curl martillo
        MusculoEjercicio(6, 33), // Curl en predicador
        MusculoEjercicio(6, 34), // Curl en polea baja
        MusculoEjercicio(6, 41), // Curl de muñeca
        MusculoEjercicio(6, 42), // Extensiones de muñeca
        MusculoEjercicio(6, 43), // Prono-supinación con barra o mancuerna
        MusculoEjercicio(6, 74), // Curl araña

        // Cuádriceps
        MusculoEjercicio(7, 16), // Peso muerto convencional
        MusculoEjercicio(7, 17),  // Peso muerto sumo
        MusculoEjercicio(7, 44), // Sentadilla con barra (convencional)
        MusculoEjercicio(7, 45), // Sentadilla con barra (frontal)
        MusculoEjercicio(7, 46), // Sentadilla con barra (búlgara)
        MusculoEjercicio(7, 48), // Prensa de piernas
        MusculoEjercicio(7, 49), // Zancadas
        MusculoEjercicio(7, 50), // Extensiones de piernas en máquina
        MusculoEjercicio(7, 69), // Box jumps
        MusculoEjercicio(7, 80), // Sentadilla hack

        // Isquiotibiales
        MusculoEjercicio(8, 16), // Peso muerto convencional
        MusculoEjercicio(8, 17),  // Peso muerto sumo
        MusculoEjercicio(8, 44), // Sentadilla con barra (convencional)
        MusculoEjercicio(8, 45), // Sentadilla con barra (frontal)
        MusculoEjercicio(8, 46), // Sentadilla con barra (búlgara)
        MusculoEjercicio(8, 47), // Peso muerto rumano
        MusculoEjercicio(8, 49), // Zancadas
        MusculoEjercicio(8, 51), // Curl femoral en máquina
        MusculoEjercicio(8, 69), // Box jumps
        MusculoEjercicio(8, 81), // Curl femoral acostado

        // Gemelos
        MusculoEjercicio(9, 53), // Elevación de talones (gemelos)
        MusculoEjercicio(9, 69), // Box jumps
        MusculoEjercicio(9, 82), // Elevación de gemelos sentado

        // Glúteos
        MusculoEjercicio(10, 16), // Peso muerto convencional
        MusculoEjercicio(10, 17),  // Peso muerto sumo
        MusculoEjercicio(10, 44), // Sentadilla con barra (convencional)
        MusculoEjercicio(10, 45), // Sentadilla con barra (frontal)
        MusculoEjercicio(10, 46), // Sentadilla con barra (búlgara)
        MusculoEjercicio(10, 47), // Peso muerto rumano
        MusculoEjercicio(10, 49), // Zancadas
        MusculoEjercicio(10, 52), // Hip thrust
        MusculoEjercicio(10, 69), // Box jumps
        MusculoEjercicio(10, 83), // Pull through en polea baja
        MusculoEjercicio(10, 84), // Patada de glúteo
        MusculoEjercicio(10, 85), // Caminata lateral con banda

        // Aductores y Abductores
        MusculoEjercicio(11, 89), // Abducción de caderas
        MusculoEjercicio(11, 90), // Aducción de caderas


        // Abdomen
        MusculoEjercicio(12, 54), // Crunch abdominal
        MusculoEjercicio(12, 55), // Crunch en máquina
        MusculoEjercicio(12, 56), // Plancha abdominal
        MusculoEjercicio(12, 57), // Elevación de piernas colgado
        MusculoEjercicio(12, 58), // Ab wheel (rueda abdominal)
        MusculoEjercicio(12, 59), // Russian twists
        MusculoEjercicio(12, 60), // Mountain climbers

        // Espalda baja
        MusculoEjercicio(13, 16), // Peso muerto convencional
        MusculoEjercicio(13, 17),  // Peso muerto sumo
        MusculoEjercicio(13, 47), // Peso muerto rumano
        MusculoEjercicio(13, 52), // Hip thrust
        MusculoEjercicio(13, 69), // Box jumps
        MusculoEjercicio(13, 80), // Sentadilla hack
        MusculoEjercicio(13, 86), // Hiperextensiones
        MusculoEjercicio(13, 87), // Superman

        //Trapecio
        MusculoEjercicio(14, 26), // Encogimientos de hombros
        MusculoEjercicio(14, 27), // Face pulls
        MusculoEjercicio(14, 78), // Dominada negativa
        MusculoEjercicio(14, 88), // Remo vertical

        //Cardio
        MusculoEjercicio(15, 61), // Cinta de correr
        MusculoEjercicio(15, 62), // Elíptica
        MusculoEjercicio(15, 63), // Bicicleta estática
        MusculoEjercicio(15, 64), // Remo ergométrico
        MusculoEjercicio(15, 65), // Assault bike
        MusculoEjercicio(15, 91), // Cuerda de batalla
        MusculoEjercicio(15, 92), // Máquina escaladora

        //Full Body
        MusculoEjercicio(16, 66), // Kettlebell swings
        MusculoEjercicio(16, 67), // Wall balls
        MusculoEjercicio(16, 68), // Burpees
        MusculoEjercicio(16, 69), // Box jumps
        MusculoEjercicio(16, 70), // Thrusters
        MusculoEjercicio(16, 71), // Dos tiempos
        MusculoEjercicio(16, 72), // Arrancada
        MusculoEjercicio(16, 73), // Muscle-ups
        MusculoEjercicio(16, 91), // Cuerda de batalla
        MusculoEjercicio(16, 93) // Jumping jacks


    )


}