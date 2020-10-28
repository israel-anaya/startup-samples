/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.ms.et.user;

public class Data {

	static public class UserInfo {
		public UserInfo(String userName, String password) {
			this.userName = userName;
			this.password = password;
		}

		public String userName;
		public String password;
	}

	static public UserInfo[] USERS = new UserInfo[] { new UserInfo("admin", "adminadmin"),
			new UserInfo("guess", "12345678"), };

	
	static public class ClientInfo {
		public ClientInfo(String clientId, String clientSecret) {
			this.clientId = clientId;
			this.clientSecret = clientSecret;
		}

		public String clientId;
		public String clientSecret;
	}

	static public ClientInfo[] CLIENTS = new ClientInfo[] { new ClientInfo("microservice", "adminadmin"),
			new ClientInfo("application", "12345678"), };
}
