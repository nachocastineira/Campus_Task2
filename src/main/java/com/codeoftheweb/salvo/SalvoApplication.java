package com.codeoftheweb.salvo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData3
			(GamePlayerRepository gamePlayerRepository, GameRepository gameRepository,
             PlayerRepository playerRepository, ShipRepository shipRepository, SalvoRepository salvoRepository,
             ScoreRepository scoreRepository) {
		return (args) -> {

			//-- DATES
			Date date = new Date();
			date = Date.from(date.toInstant());
			Date date2 = new Date();
			date2 = Date.from(date2.toInstant().plusSeconds(6000));

			//-- GAMES
			Game game1 = new Game();
			game1.setCreationDate(date);
			Game game2 = new Game();
			game2.setCreationDate(date2);
			Game game3 = new Game();
			game3.setCreationDate(date);
			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);

			//-- PLAYERS
			Player player1 = new Player("j.bauer@ctu.gov", "24");
			Player player2 = new Player("c.obrian@ctu.gov", "42");
			Player player3 = new Player("kim_bauer@gmail.com", "kb");
			Player player4 = new Player("t.almeida@ctu.gov", "mole");
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);


			//-- GAMEPLAYERS
			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player3);
//			GamePlayer gamePlayer3 = new GamePlayer(game2, player1);
//			GamePlayer gamePlayer4 = new GamePlayer(game2, player2);
//			GamePlayer gamePlayer5 = new GamePlayer(game1, player3);
//			GamePlayer gamePlayer6 = new GamePlayer(game3, player2);
//			GamePlayer gamePlayer7 = new GamePlayer(game1, player1);
			gamePlayerRepository.save(gamePlayer1);
			gamePlayerRepository.save(gamePlayer2);
//			gamePlayerRepository.save(gamePlayer3);
//			gamePlayerRepository.save(gamePlayer4);
//			gamePlayerRepository.save(gamePlayer5);
//			gamePlayerRepository.save(gamePlayer6);
//			gamePlayerRepository.save(gamePlayer7);

			//--LOCATIONS SHIP
			List<String> shipLocations1 = new ArrayList<>();
			shipLocations1.add("H2");
			shipLocations1.add("H3");
			shipLocations1.add("H4");
			List<String> shipLocations2 = new ArrayList<>();
			shipLocations2.add("F5");
			shipLocations2.add("F6");
			shipLocations2.add("F7");
			List<String> shipLocations3 = new ArrayList<>();
			shipLocations3.add("D4");
			shipLocations3.add("E4");
			shipLocations3.add("F4");
			List<String> shipLocations4 = new ArrayList<>();
			shipLocations4.add("A10");
			shipLocations4.add("B10");
			shipLocations4.add("C10");

			//---SHIPS
			Ship ship1 = new Ship(gamePlayer1, shipLocations1, "Destructor"); //Game 1 -> Player 1
			Ship ship2 = new Ship(gamePlayer1, shipLocations3, "Submarine");  //Game 1 -> Player 1
			Ship ship3 = new Ship(gamePlayer2, shipLocations2, "Cruiser");    //Game 1 -> Player 3
			Ship ship4 = new Ship(gamePlayer2, shipLocations4, "Patrol Boat");    //Game 1 -> Player 3
			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);

			//--LOCATIONS SALVOES
			List<String> salvoLocation1 = new ArrayList<>();
			salvoLocation1.add("D4");
			salvoLocation1.add("H2");
			salvoLocation1.add("F5");
			List<String> salvoLocation2 = new ArrayList<>();
			salvoLocation2.add("A10");
			salvoLocation2.add("H4");
			salvoLocation2.add("H2");
			List<String> salvoLocation3 = new ArrayList<>();
			salvoLocation3.add("C10");
			List<String> salvoLocation4 = new ArrayList<>();
			salvoLocation4.add("H3");
			List<String> salvoLocation5 = new ArrayList<>();
			salvoLocation5.add("F6");
			List<String> salvoLocation6 = new ArrayList<>();
			salvoLocation6.add("D4");

			//-- SALVOES
			Salvo salvo1 = new Salvo(gamePlayer1, "1", salvoLocation1);
			Salvo salvo2 = new Salvo(gamePlayer2, "1", salvoLocation2);
			Salvo salvo3 = new Salvo(gamePlayer1, "2", salvoLocation3);
			Salvo salvo4 = new Salvo(gamePlayer2, "2", salvoLocation4);
			Salvo salvo5 = new Salvo(gamePlayer1, "3", salvoLocation5);
			Salvo salvo6 = new Salvo(gamePlayer2, "3", salvoLocation6);
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);

			//-- SCORES
/*			float win = 1;
			float tied = (float)0.5;
			float lost = 0;*/

			Score score1 = new Score (game1, player1, 1, date);
			Score score2 = new Score (game1, player2, 0.5, date);
			Score score3 = new Score (game2, player2, 0.5, date);
			Score score4 = new Score (game2, player3, 1, date);
			Score score5 = new Score (game3, player3, 0.5, date);
			Score score6 = new Score (game3, player1, 0.5, date);

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);

		};
	}
}


@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {


	@Bean
	public UserDetailsService loadUserByUsername() {
		UserDetails user =
				User.withDefaultPasswordEncoder()
						.username("user")
						.password("password")
						.roles("USER")
						.build();

		return new InMemoryUserDetailsManager(user);
	}
}


@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/rest").permitAll()
				.antMatchers("/").permitAll()
				.and()
				.formLogin();
//				.loginPage("/")
//				.permitAll();
//				.and()
//				.logout()
//				.permitAll();
	}


}