package br.com.devon.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.com.devon.util.DataUtil;

public class TestDev {
	
	public static void main(String[] args) {
		
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 12));
		System.out.println(DataUtil.isAposDezesseisHoras(ldt));
		
	}

	

}
