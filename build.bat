echo OFF
echo -----------------------------------------------------------------
echo Samples Startup Framework
echo -----------------------------------------------------------------

call mvn -f sample-domain-user/pom.xml clean install
call mvn -f sample-domain-customer/pom.xml clean install

call mvn -f sample-ms-et-user/pom.xml clean package
call mvn -f sample-ms-et-customer/pom.xml clean package
call mvn -f sample-ms-mt-customer-identity/pom.xml clean package
call mvn -f sample-ms-ts-customer/pom.xml clean package
