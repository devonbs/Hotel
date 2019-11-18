package br.com.devon.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataUtil {
	
	public static void trataData(String data) {
		
	}
	
	public static long contaIntervaloDatas(LocalDateTime dataEntrada, LocalDateTime dataSaida) {
		return ChronoUnit.DAYS.between(dataEntrada, dataSaida);
	}

	public static boolean isFinalSemana(LocalDateTime ldt) {
		DayOfWeek dof = ldt.getDayOfWeek();
		return(DayOfWeek.SATURDAY.equals(dof) || DayOfWeek.SUNDAY.equals(dof));
	}

	public static LocalDateTime converteDataLocalDate(String data) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(data, dtf);
	}
	
	public static boolean isAposDezesseisHoras(LocalDateTime data) {
		LocalTime lt = data.toLocalTime();
		LocalTime hora = LocalTime.of(16, 00);
		return lt.isAfter(hora);
	}

}
