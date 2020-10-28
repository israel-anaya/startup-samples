package sample.ms.et.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sample.ms.et.user.Data.ClientInfo;
import sample.ms.et.user.Data.UserInfo;
import sample.ms.et.user.entity.ClientEntity;
import sample.ms.et.user.entity.UserEntity;
import sample.ms.et.user.service.ClientService;
import sample.ms.et.user.service.UserService;


/**
 * Load initial data
 * 
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Component
@Order(2)
public class InitialDataDBLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(InitialDataDBLoader.class);

	@Autowired
	UserService userService;

	@Autowired
	ClientService clientService;

	void createUsers() {
		for (UserInfo userInfo : Data.USERS) {

			UserEntity buffer = userService.findByUserName(userInfo.userName);
			if (buffer == null) {
				buffer = new UserEntity();
				buffer.setUserName(userInfo.userName);
				buffer.setPassword(userInfo.password);
				userService.save(buffer);
			}
		}
	}

	void createClients() {
		for (ClientInfo clientInfo : Data.CLIENTS) {

			ClientEntity buffer = clientService.findByClientId(clientInfo.clientId);
			if (buffer == null) {
				buffer = new ClientEntity();
				buffer.setClientId(clientInfo.clientId);
				buffer.setClientSecret(clientInfo.clientSecret);
				clientService.save(buffer);
			}
		}
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Load initial users...");

		createUsers();
		createClients();

		logger.info("Loaded users.");

	}

}
