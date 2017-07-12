package cn.timebusker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// spring boot实践
		SpringApplication.run(App.class, args);
		aut();
	}

	private static void aut() {
		int x, y;
		for (x = 0; x <= 9; x++) {
			for (y = 1; y <= x; y++) {
				System.out.print(y + "*" + x + "=" + x * y + "\t");
			}
			System.out.println();
		}
	}
}