package Cine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FechaEstreno {

    public static LocalDateTime calcularProximoViernes() {
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime proximoViernes = hoy.with(DayOfWeek.FRIDAY).withHour(17).withMinute(0).withSecond(0);

        if (hoy.isAfter(proximoViernes)) {
            proximoViernes = proximoViernes.plusWeeks(1);
        }
        guardarenTxt(proximoViernes);
        return proximoViernes;
    }

    public static void guardarenTxt(LocalDateTime fecha) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("proximo_viernes.txt"))) {
            writer.write(fecha.toString());
        } catch (Exception e) {
            System.out.println("Esto estaba bien hace 5min, que hiciste??");
        }
    }

    public static boolean viernesFinal() {
        try {
            List<String> lineas = Files.readAllLines(Paths.get("proximo_viernes.txt"));
            if (!lineas.isEmpty()) {
                String ultimaFecha = lineas.get(lineas.size() - 1);
                LocalDateTime fechaGuardada = LocalDateTime.parse(ultimaFecha);

                LocalDateTime ahora = LocalDateTime.now();

                if (ahora.isAfter(fechaGuardada)) {
                    Files.deleteIfExists(Paths.get("proximo_viernes.txt"));
                    return false;
                }
                return ahora.isEqual(fechaGuardada);
            }
        } catch (Exception e) {
            System.out.println("Error al leer tu fecha, consulta con un medico :)");
        }
        return false;
    }

    public static String viernesqueviene() {
        LocalDateTime proximoViernes = calcularProximoViernes();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy 'a las' hh:mm a");
        return "|♣ La siguiente función será el viernes " + proximoViernes.format(formato) + " |";
    }

}






