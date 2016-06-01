#!/usr/bin/env groovy

import groovy.sql.Sql
import groovy.json.*

// define console for user input
def cons = System.console()

// load server connection properties
Properties props = new Properties()
File propsFile = new File('./application.properties')
props.load(propsFile.newDataInputStream())
String server = props.getProperty('server')
String port = props.getProperty('port')
String database = props.getProperty('database')
String login = props.getProperty('login')

def passwd = cons.readPassword("Password: ")

sql = Sql.newInstance(
	"jdbc:sybase:Tds:${server}:${port}/${database}?JCONNECT_VERSION=6&CHARSET=cp850",
	"${login}",
	"${passwd}",
	"com.sybase.jdbc3.jdbc.SybDriver")

String storedProcedureCall
def params
int returnValue
int colorIndex

println("\nTesting stored procedure up_raise_error")
def mynum = cons.readLine("myNum: ")

storedProcedureCall = "{? = call up_raise_error(?)}"
params = [Sql.INTEGER,mynum.toInteger()]
sql.call(storedProcedureCall, params) {rv ->
  returnValue = rv
}
println("returnValue: ${returnValue}")


println("\nTesting stored procedure up_multi_params")
def myColor = cons.readLine("myColor: ")

storedProcedureCall = "{? = call up_multi_params(?,?)}"
params = [Sql.INTEGER,myColor,Sql.INTEGER]
sql.call(storedProcedureCall, params) {rv,ci ->
  returnValue = rv
  colorIndex = ci
}
println("returnValue: ${returnValue}")
println("colorIndex: ${colorIndex}")
