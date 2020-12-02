echo OFF
echo -----------------------------------------------------------------
echo Samples Startup Framework
echo -----------------------------------------------------------------

call mvn -f sample-customer-domain/pom.xml clean
call mvn -f sample-customer-ms-et/pom.xml clean
call mvn -f sample-customer-identity-ms-mt/pom.xml clean
call mvn -f sample-customer-ms-ts/pom.xml clean


