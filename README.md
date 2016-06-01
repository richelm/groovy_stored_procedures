# Groovy Stored Procedures

Making use of [Groovy Sql class](http://docs.groovy-lang.org/latest/html/api/groovy/sql/Sql.html) can make calling stored procedures much easier in a Java environment. This project provides use cases that can be expanding for CRUD in a JSF application. That was the motivation for this exercise. SAP (Sybase) ASE 15.7 was the DBMS for this project, but any DBMS that has stored procedures can be substituted.

1. Stored procedure with input parameter(s) and return value.
1. Stored procedure with input parameter(s), return value and one return parameter.
1. Stored procedure with input parameter(s), return value and one result set.

For cases 1 and 2 we use method call with the following syntax:

```
public void call(String sql,
        List<Object> params,
        Closure closure)
          throws Exception
```

For case 3  we use method call with the following syntax:

```
public List<GroovyRowResult> callWithRows(String sql,
                                 List<Object> params,
                                 Closure closure)
                                   throws SQLException
```

## References

1. [Simpler Stored Procedures with Groovy](https://objectpartners.com/2014/01/24/simpler-stored-procedures-with-groovy/)
