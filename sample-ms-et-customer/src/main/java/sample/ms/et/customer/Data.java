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

package sample.ms.et.customer;


public class Data {

	static public class EndpointInfo {
		public EndpointInfo(String path) {
			this.path = path;
		}

		public String path;
	}
	
	static public class ChannelInfo {
		public ChannelInfo(String name) {
			this.name = name;
		}

		public String name;
	}
	
	static public class SecurityOperationInfo {
		public SecurityOperationInfo(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String id;
		public String name;
	}

	static public ChannelInfo[] CHANNELS = new ChannelInfo[] {
			new ChannelInfo("BPT-FWS-F"),
			new ChannelInfo("BPT-FWS"),
			new ChannelInfo("BPT-MOB"),
			new ChannelInfo("WALMART-MOB"), };

	/*static public SecurityOperationInfo[] OPERATIONS = new SecurityOperationInfo[] {
			new SecurityOperationInfo("67ec3f7d-e7f2-4f3c-b931-0e0d86acc936", "baas-security-ms-iam-user-bpt"),
	};*/

	static public EndpointInfo[] SECURITY_ENDPOINTS = new EndpointInfo[] {
			new EndpointInfo("/security/iam/token/anonymous"),
			new EndpointInfo("/security/iam/token/identify"),
			new EndpointInfo("/security/iam/token/signin"),
			new EndpointInfo("/security/iam/token/signout"), };

	static public EndpointInfo[] CATSEX_ENDPOINTS = new EndpointInfo[] {
			new EndpointInfo("/mx-ms-xapi-cross-microservice/sextype/level00"),
			new EndpointInfo("/mx-ms-xapi-cross-microservice/sextype/level10"),
			new EndpointInfo("/mx-ms-xapi-cross-microservice/sextype/level20"),
			new EndpointInfo("/mx-ms-xapi-cross-microservice/sextype/level30"), };

	public static class EndpointGroupInfo {
		public EndpointGroupInfo(String name, EndpointInfo[] endpoints) {
			this.name = name;
			this.endpoints = endpoints;
		}

		public String name;
		public EndpointInfo[] endpoints;
	}

	static public EndpointGroupInfo[] ENDPOINT_GROUPS = new EndpointGroupInfo[] {
			new EndpointGroupInfo("Security", SECURITY_ENDPOINTS),
			new EndpointGroupInfo("Catalogs", CATSEX_ENDPOINTS), };
}
