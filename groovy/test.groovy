#!/usr/bin/env groovy

import groovy.sql.Sql

// define console for user input
def cons = System.console()

// load server connection properties
Properties props = new Properties()
File propsFile = new File('./sqlserver.properties')
props.load(propsFile.newDataInputStream())
String url = props.getProperty('url')
String driver = props.getProperty('driver')
String login = props.getProperty('login')

def passwd = cons.readPassword("Password: ")

sql = Sql.newInstance("${url}","${login}","${passwd}","${driver}")

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


// println("\nTesting stored procedure up_retrieve_employee")
def empid = cons.readLine("Employee ID: ")

storedProcedureCall = "execute up_retrieve_employee ?"
params = [empid.toInteger()]
def rows = sql.rows(storedProcedureCall, params)
println rows.join('\n')
