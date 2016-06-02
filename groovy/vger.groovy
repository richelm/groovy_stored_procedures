#!/usr/bin/env groovy

import groovy.sql.Sql
import groovy.sql.GroovyRowResult

// load server connection properties
Properties props = new Properties()
File propsFile = new File('./application.properties')
props.load(propsFile.newDataInputStream())
String server = props.getProperty('server')
String port = props.getProperty('port')
String database = props.getProperty('database')
String login = props.getProperty('login')
String passwd = props.getProperty('passwd')

int returnValue
int dummy

sql = Sql.newInstance(
	"jdbc:jtds:sybase://${server}:${port}/${database};charset=cp850",
	"${login}",
	"${passwd}",
  "net.sourceforge.jtds.jdbc.Driver")

// jConnect
// "jdbc:sybase:Tds:${server}:${port}/${database}?CHARSET=cp850"
// "com.sybase.jdbc4.jdbc.SybDriver"
// jTDS
//"jdbc:jtds:sybase://${server}:${port}/${database};charset=cp850"
// "net.sourceforge.jtds.jdbc.Driver"

def empid = 103

println("\nTEST 1: Execute up_retrieve_employee with sql.callAllWithRows")
// def rowsList = sql.callWithAllRows "{call up_retrieve_employee($empid)", {}
// //println "returnValue: ${returnValue}"
// println rowsList.getClass().getName()
// println rowsList.toString()

def rowsList = sql.callWithAllRows("up_retrieve_employee ?", [103],{})
println rowsList.getClass().getName()
println rowsList.toString()

println("\nTEST 2: Execute up_retrieve_employee with sql.callWithRows")
storedProcedureCall = "{? = call up_retrieve_employee(?)}"
try {
  def rows = sql.callWithRows storedProcedureCall, [Sql.INTEGER,empid], {
    int rv, List<GroovyRowResult> rs ->
    returnValue = rv
  }
  println "returnValue: ${returnValue}"
  print "Rows: "
  println rows.getClass().getName()
  println rows.toString()
}  catch (all) {
  all.printStackTrace()
}


println("\nExecute up_retrieve_employee with sql.rows")
storedProcedureCall = "execute up_retrieve_employee ?"
params = [empid.toInteger()]
def empRows = sql.rows(storedProcedureCall, params)
println empRows.join('\n')
